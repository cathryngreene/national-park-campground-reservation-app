package com.techelevator.model.JDBC;


import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import org.omg.DynamicAny.DynAnySeqHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.CampgroundCLI;
import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.Reservation;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

public class JDBCsiteDAO implements SiteDAO {
	
	private JdbcTemplate jdbcTemplate;
	ArrayList<Site> availableSites = new ArrayList<>();  
	ArrayList<Integer> selectSites = new ArrayList<>();  

	public JDBCsiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void getSiteResults(String campName, String arrival, String departure) {        
		String sqlGetSiteResults = "SELECT site_number, max_occupancy, accessible, max_rv_length, utilities, campground.daily_fee "+ 
				   "FROM site "+
				   "JOIN campground "+
				   "ON site.campground_id = campground.campground_id "+
				   "WHERE campground.name = ? "+
				   "AND open_from_mm <= ? "+
				   "AND open_to_mm >= ? "+
				   "AND site_id NOT IN (SELECT site_id "
				   					+ "FROM reservation "
				   					+ "WHERE reservation.from_date NOT BETWEEN to_date(?, 'YYYY-MM-DD') AND to_date(?, 'YYYY-MM-DD') "  
				   					+ "AND   reservation.to_date   NOT BETWEEN to_date(?, 'YYYY-MM-DD') AND to_date(?, 'YYYY-MM-DD')) "
				   + "ORDER BY max_occupancy "
				   + "LIMIT 5 ";
				   
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteResults, campName,
																	     arrival.substring(5,7),
																	     departure.substring(5,7),
																		 arrival, departure,
																		 arrival, departure);
		
		 LocalDate arrivalDate = LocalDate.parse(arrival);
	     LocalDate depatureDate = LocalDate.parse(departure);
	     Long difference = ChronoUnit.DAYS.between(arrivalDate, depatureDate);
	     Double stayDuration = difference.doubleValue();	
	     ArrayList<Campground> fee = new ArrayList<>();
		 while(results.next()) {
			Site thisSite = mapRowToSite(results);
			Campground thisCampground = mapRowToFee(results);
			availableSites.add(thisSite);	
			fee.add(thisCampground);
		}
		 
		if (availableSites.size() == 0) {
			System.out.print("No results found for input query. Please enter an alternate date range.");
			
		}
		else {
		System.out.println("SITE #   " + "MAX OCCUP.    " + "ACCESSIBLE    " + "MAX RV LENGTH   " + "UTILITY       " + "COST");
		for (int i = 0; i < availableSites.size(); i++) {
			System.out.println((String.format("%-8s %-13s %-13s %-15s %-13s %-1s" , availableSites.get(i).getSiteNumber() , availableSites.get(i).getMaxOccupancy() ,
							   availableSites.get(i).isAccessibleString() , availableSites.get(i).getMaxRVLength() ,
							   availableSites.get(i).isUtilitiesString() , "$")) + String.format("%.2f",fee.get(i).getDailyFee()*stayDuration));
		}
		}
	}

 public ArrayList<Integer> returnAvaiableSites(){
	for (int i = 0; i <availableSites.size(); i++) {
		selectSites.add(availableSites.get(i).getSiteNumber());
	}
	return selectSites;
 }
	
	

private Site mapRowToSite(SqlRowSet results) {
	Site thisSite;
	thisSite = new Site();
	thisSite.setSiteNumber(results.getInt("site_number"));
	thisSite.setMaxOccupancy(results.getInt("max_occupancy"));
	thisSite.setAccessible(results.getBoolean("accessible"));
	thisSite.setMaxRVLength(results.getInt("max_rv_length"));
	thisSite.setUtilities(results.getBoolean("utilities"));
	return thisSite;
}

private Campground mapRowToFee(SqlRowSet results) {
	Campground thisCampgorund;
	thisCampgorund = new Campground();
	thisCampgorund.setDailyFee(results.getDouble("daily_fee"));
	return thisCampgorund;
}
}// END OF SITE DAO
