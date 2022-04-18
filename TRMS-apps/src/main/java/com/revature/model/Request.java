package com.revature.model;

import java.sql.Date;
import java.time.LocalDate;

public class Request {
	//fields
	private int requestId;
	protected int submitterId;
	protected int eventId;
	private int statusId;
	private double cost;
	private String description;
	private String location;
	private Date eventDate;
	
	//
	public Request() {
		requestId = 0;
		submitterId = 0;
		eventId = 0;
		statusId = 0;
		cost = 0;
		description = "";
		location = "";
		eventDate = Date.valueOf(LocalDate.now());
	}

	//getters and setters
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(int submitterId) {
		this.submitterId = submitterId;
	}

	public int getEventId() {
		return eventId;
	}
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", submitterId" + submitterId + ", eventId" + eventId +
				", statusId" + statusId + ", eventDate" + eventDate + ", cost" + cost + ", description" +
				description + ", location" + location + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestId;
		result = prime * result + submitterId;
		result = prime * result + eventId;
		result = prime * result + statusId;
		result = prime * result + ((eventDate== null)? 0 : eventDate.hashCode());
		result = (int) (prime * result + cost) ;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((location == null)? 0 : location.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		
		if (requestId != other.requestId)
			return false;
		if (submitterId != other.submitterId)
			return false;
		if (eventId != other.eventId)
			return false;
		if (statusId != other.statusId)
			return false;
		
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	
	

}
