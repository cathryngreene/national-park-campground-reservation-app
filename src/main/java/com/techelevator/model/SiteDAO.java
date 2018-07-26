package com.techelevator.model;

import java.util.ArrayList;

public interface SiteDAO {
	
	public void getSiteResults(String campName, String arrival, String departure);
	// println all sites with applicable information (using getters) 
	// only print sites that are availabe given the dates the user has entered

	 public ArrayList<Integer>	returnAvaiableSites();
			 
}


