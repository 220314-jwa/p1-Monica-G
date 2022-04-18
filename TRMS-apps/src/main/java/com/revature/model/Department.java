package com.revature.model;


//import java.util.List;

public class Department extends Employee{

	private int deptId;
	private int deptHeadId;
	private String deptName;
	//private List<Employee> labor;
	
	//constructor
	public Department() {
		deptId = 0;
		deptHeadId = 0;
		deptName = "";
		//labor = new ArrayList<Employee>();
	}
	
	//getters and setters
	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getDeptHeadId() {
		return deptHeadId;
	}

	public void setDeptHeadId(int deptHeadId) {
		this.deptHeadId = deptHeadId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	@Override
	public String toString() {
		return "Department [deptId= " + deptId + ", deptHeadId" + deptHeadId + 
				", deptName" + deptName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptId;
		result = prime * result + deptHeadId;
		result = prime * result + ((deptName == null)? 0 : deptName.hashCode());
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
		Department other = (Department) obj;
		
		if (deptId != other.deptId)
			return false;
		if (deptHeadId != other.deptHeadId)
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		return true;
	}
	

	
	
	

}
