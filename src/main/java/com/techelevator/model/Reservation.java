package com.techelevator.model;

import java.time.*;

public class Reservation {
		// attributes
	private Long reservationId;
	private Integer siteId;
	private String name;
	private Integer fromDate;
	private Integer toDate;
	private Integer createDate;
	
		// methods
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFromDate() {
		return fromDate;
	}
	public void setFromDate(Integer fromDate) {
		this.fromDate = fromDate;
	}
	public Integer getToDate() {
		return toDate;
	}
	public void setToDate(Integer toDate) {
		this.toDate = toDate;
	}
	public Integer getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Integer createDate) {
		this.createDate = createDate;
	}
}
