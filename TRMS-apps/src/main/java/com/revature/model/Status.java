package com.revature.model;

public class Status  {
	private int statId;
	private String statName;
	
	//constructor
	public Status() {
		statId = 0;
		statName = "";
	}

	//getters and setters
	public int getStatId() {
		return statId;
	}

	public void setStatId(int statId) {
		this.statId = statId;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}
	
	//override
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + statId;
		result = prime * result + ((statName == null)? 0 : statName.hashCode());
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
		Status other = (Status) obj;
		
		if (statId != other.statId)
			return false;
		
		if (statName == null) {
			if (other.statName != null)
				return false;
		} else if (!statName.equals(other.statName))
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		return "User [statId=" + statId + ", username=" + statName +"]";
	}
	
	
	
	
}
