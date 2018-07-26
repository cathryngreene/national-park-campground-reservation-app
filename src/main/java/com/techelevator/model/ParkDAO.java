package com.techelevator.model;

import java.util.ArrayList;

public interface ParkDAO {

	public ArrayList<Park> getAllParks();
	// return all park names in an Array List
	
	public void parkInformation(Object parkName);
	// getters for Park table columns
	

}
