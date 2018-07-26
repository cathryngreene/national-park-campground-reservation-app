package com.techelevator.model;

public interface ReservationDAO {

	public Long makeReservation(Integer siteNumber, String reserveName, String arrival, String departure, String today);

}
