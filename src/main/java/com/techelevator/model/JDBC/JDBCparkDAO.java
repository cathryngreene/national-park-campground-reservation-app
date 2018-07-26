package com.techelevator.model.JDBC;

import java.util.ArrayList;
import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;

public class JDBCparkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	
	public JDBCparkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	ArrayList<Park> parks = new ArrayList<>();

	@Override
	public ArrayList<Park> getAllParks() {
		String sqlGetAllParks = "SELECT name " +
								"FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park thisPark = mapRowToParkName(results);
			parks.add(thisPark);
		}
		return parks;
	}
	
	@Override
	public void parkInformation(Object parkName) { //need to include the 'name' of selected park as an arg
		ArrayList<Park> parkInfo = new ArrayList<>();
		String sqlParkInformation = "SELECT * "+
									"FROM park "+
									"WHERE name = ?"; //we need to get the 'name' of the selected park(see below)
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlParkInformation, parkName);
		while (results.next()) {
			Park thisPark = mapRowToPark(results);
			parkInfo.add(thisPark);
		}
		System.out.println("Park ID: " + parkInfo.get(0).getParkID());
		System.out.println("Location: " + parkInfo.get(0).getLocation());
		System.out.println("Established: " + parkInfo.get(0).getEstablishDate());
		System.out.println("Area: " + parkInfo.get(0).getArea());	
		System.out.println("Annual Visitors: " + parkInfo.get(0).getVisitors());	
		System.out.println("Description: \n" + stringLengthSet(parkInfo.get(0).getDescription(), 50, Locale.getDefault()));   //(parkInfo.get(0).getDescription());	
	}
		

	private Park mapRowToParkName(SqlRowSet results) {
		Park thisPark;
		thisPark = new Park();
		thisPark.setName(results.getString("name"));
		return thisPark;
	}
	
	private Park mapRowToPark(SqlRowSet results) {
		Park thisPark;
		thisPark = new Park();
		thisPark.setParkID(results.getInt("park_id"));
		thisPark.setName(results.getString("name"));
		thisPark.setLocation(results.getString("location"));
		thisPark.setEstablishDate(results.getDate("establish_date"));
		thisPark.setArea(results.getInt("area"));
		thisPark.setVisitors(results.getInt("visitors"));
		thisPark.setDescription(results.getString("description"));
		return thisPark;
	}
	
	 public static String stringLengthSet(String input, int width, Locale locale) {
	        if (input == null) {
	            return "";
	        }
	        else if (width < 5) {
	            return input;
	        }
	        else if (width >= input.length()) {
	            return input;
	        }
	        StringBuilder set = new StringBuilder(input);
	        boolean endOfLine = false;
	        int lineStart = 0;
	        for (int i = 0; i < set.length(); i++) {
	            if (set.charAt(i) == '\n') {
	                lineStart = i + 1;
	                endOfLine = true;
	            }
	            if (i > lineStart + width - 1) {
	                if (!endOfLine) {
	                    int limit = i - lineStart - 1;
	                    BreakIterator breaks = BreakIterator.getLineInstance(locale);
	                    breaks.setText(set.substring(lineStart, i));
	                    int end = breaks.last();

	                    if (end == limit + 1) {
	                        if (!Character.isWhitespace(set.charAt(lineStart + end))) {
	                            end = breaks.preceding(end - 1);
	                        }
	                    }
	                    // if the last character is a space, replace it with a \n
	                    if (end != BreakIterator.DONE && end == limit + 1) {
	                    	set.replace(lineStart + end, lineStart + end + 1, "\n");
	                        lineStart = lineStart + end;
	                    }
	                    // otherwise, just insert a \n
	                    else if (end != BreakIterator.DONE && end != 0) {
	                    	set.insert(lineStart + end, '\n');
	                        lineStart = lineStart + end + 1;
	                    }
	                    else {
	                    	set.insert(i, '\n');
	                        lineStart = i + 1;
	                    }
	                }
	                else {
	                	set.insert(i, '\n');
	                    lineStart = i + 1;
	                    endOfLine = false;
	                }
	            }
	        }
	        return set.toString();
	    }

	}
 //END OF PARK DAO
