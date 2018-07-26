package com.techelevator.model;


public class Campground {
		// attributes
	private Integer campgroundId;
	private Integer parkId;
	private String name;
	private Integer openFrom;
	private Integer openUntil;
	private Double dailyFee;

		// methods
	public Integer getCampgroundId() {
		return campgroundId;
}
	public void setCampgroundId(Integer campgroundId) {
		this.campgroundId = campgroundId;
	}
	public Integer getParkId() {
		return parkId;
	}
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(Integer openFrom) {
		this.openFrom = openFrom;
	}
 	public String getOpenFromString() {
		if (openFrom == 1) {
			return "January";
		}
		else if (openFrom == 2) {
			return "February";
		}
		else if (openFrom == 3) {
			return "March";
		}
		else if (openFrom == 4) {
			return "April";
		}
		else if (openFrom == 5) {
			return "May";
		}
		else if (openFrom == 6) {
			return "June";
		}
		else if (openFrom == 7) {
			return "July";
		}
		else if (openFrom == 8) {
			return "August";
		}
		else if (openFrom == 9) {
			return "September";
		}
		else if (openFrom == 10) {
			return "October";
		}
		else if (openUntil == 11) {
			return "November";
		}
		else {
			return "December";
		}
}
	public Integer getOpenUntil() {
		return openUntil;
	}
 	public void setOpenUntil(Integer openUntil) {
		this.openUntil = openUntil;
	}
 	
 	public String getOpenUntilString() {
 		
		if (openUntil == 1) {
			return "January";
		}
		else if (openUntil == 2) {
			return "February";
		}
		else if (openUntil == 3) {
			return "March";
		}
		else if (openUntil == 4) {
			return "April";
		}
		else if (openUntil == 5) {
			return "May";
		}
		else if (openUntil == 6) {
			return "June";
		}
		else if (openUntil == 7) {
			return "July";
		}
		else if (openUntil == 8) {
			return "August";
		}
		else if (openUntil == 9) {
			return "September";
		}
		else if (openUntil == 10) {
			return "October";
		}
		else if (openUntil == 11) {
			return "November";
		}
		else {
			return "December";
		}
}	
	public Double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(Double dailyFee) {
		this.dailyFee = dailyFee ;
	}
}
