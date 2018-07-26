package com.techelevator.model;

public class Site {
		// attributes
	private Integer siteId;
	private Integer campgroundId;
	private Integer siteNumber;
	private Integer maxOccupancy;
	private boolean accessible;
	private Integer maxRVLength;
	private boolean utilities;
	
		// methods
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Integer getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(Integer campgroundId) {
		this.campgroundId = campgroundId;
	}
	public Integer getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(Integer siteNumber) {
		this.siteNumber = siteNumber;
	}
	public Integer getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(Integer maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public String isAccessibleString() {
		if (accessible == true) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
	public Integer getMaxRVLength() {
		return maxRVLength;
	}
	public void setMaxRVLength(Integer maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	public boolean isUtilities() {
		return utilities;
	}
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	public String isUtilitiesString() {
		if (utilities == true) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
}
