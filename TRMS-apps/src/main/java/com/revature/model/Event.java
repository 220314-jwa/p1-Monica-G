package com.revature.model;

public class Event {
	private int eventId;
	private String eventName;
	
	
	//constructor
	public Event () {
		eventId = 0;
		eventName = "";
		
	}

	//getters and setters
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Event other = (Event) obj;
		if (getClass() != obj.getClass())
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		return true;
	}

	
	
}
