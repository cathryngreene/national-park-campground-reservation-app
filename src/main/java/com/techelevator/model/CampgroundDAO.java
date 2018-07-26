package com.techelevator.model;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

public interface CampgroundDAO {
	
	public ArrayList<Campground> displayCampgrounds(Object parkName);
	// return a list of campground objects, use getters on the Array elements to show 
	// the campgrounds information. print out to console.

}
