package com.techelevator.model.JDBC;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;

public class JDBCcampgroundDAO implements CampgroundDAO{

	private JdbcTemplate jdbcTemplate;

	public JDBCcampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public ArrayList<Campground> displayCampgrounds(Object parkName) {
		ArrayList<Campground> campgrounds = new ArrayList<>();
		String sqlDisplayCampgrounds = "SELECT campground.name, open_from_mm, open_to_mm, daily_fee " +
									   "FROM campground "+
									   "JOIN park "+
									   "ON campground.park_id = park.park_id "+
									   "WHERE park.name = ? "+"\n"  ; 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlDisplayCampgrounds, parkName);
		while(results.next()) {
			Campground thisCampground = mapRowToCampground(results);
			campgrounds.add(thisCampground);
		}

		System.out.println("   NAME" + "		                   OPEN" + "         CLOSE" + "	        DAILY FEE");
		for (int i = 0; i < campgrounds.size(); i++) {
		System.out.println("#" + (i+1) + " " + (String.format("%-31s %-12s %-15s %-1s" , campgrounds.get(i).getName() , campgrounds.get(i).getOpenFromString() ,
										campgrounds.get(i).getOpenUntilString() , "$") + String.format("%.2f",campgrounds.get(i).getDailyFee()))); 
		
		}
		return campgrounds;
	}
	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground thisCampground;
		thisCampground = new Campground();
		thisCampground.setName(results.getString("name"));
		thisCampground.setOpenFrom(results.getInt("open_from_mm"));
		thisCampground.setOpenUntil(results.getInt("open_to_mm"));
		thisCampground.setDailyFee(results.getDouble("daily_fee"));
		return thisCampground;
	}
} //	END OF CAMPGROUND DAO
