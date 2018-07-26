package com.techelevator.model.JDBC;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.ReservationDAO;

public class JDBCreservationDAO implements ReservationDAO{
	
	private JdbcTemplate jdbcTemplate;

	public JDBCreservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Long makeReservation(Integer siteNumber, String reserveName, String arrival, String departure, String today) {
	String sqlMakeReservation = "INSERT INTO reservation(site_id, name, from_date, to_date, create_date) "
	+ "VALUES(?, ?, to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD')) "
	+ "RETURNING reservation_id";
	return jdbcTemplate.queryForObject(sqlMakeReservation, Long.class, siteNumber,
	reserveName,
	arrival,
	departure,
	today);
	
	}

	
	
	
	
	
	
	
	
	
}// END OF RESERVATION
