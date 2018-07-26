package com.techelevator.model;

import java.time.*;
import java.util.Date;

public class Park {
		// attributes
	private Integer parkID;
	private String name;
	private String location;
	private Date establishDate;
	private Integer area;
	private Integer visitors;
	private String description;
	
		// methods
	public Integer getParkID() {
		return parkID;
	}
	public void setParkID(Integer parkID) {
		this.parkID = parkID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getVisitors() {
		return visitors;
	}
	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
