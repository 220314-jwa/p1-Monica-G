package com.revature.model;

public class Employee  {
	
		private int employeeId;
		private int managerId;
		private int deptId;
		private String fName; 
		private String lName;
		
		//constructor
		public Employee () {
			employeeId = 0;
			managerId = 0;
			deptId = 0;
			fName = "";
			lName = "";
		}
		
		public int getEmployeeId() {
			return employeeId;
		}
		
		//getters and setters
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public int getManagerId() {
			return managerId;
		}
		public void setManagerId(int managerId) {
			this.managerId = managerId;
		}
		public int getDeptId() {
			return deptId;
		}
		public void setDeptId(int deptId) {
			this.deptId = deptId;
		}
		public String getfName() {
			return fName;
		}
		public void setfName(String fName) {
			this.fName = fName;
		}
		public String getlName() {
			return lName;
		}
		public void setlName(String lName) {
			this.lName = lName;
		}

		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if(obj ==null || getClass() != obj.getClass()) return false;
			Employee other = (Employee) obj;
			
			if( managerId == other.managerId)
				return false;
			if(deptId == other.deptId)
				return false;
			if(employeeId == other.employeeId)
				return false;
			if (fName == null) {
				if(other.fName != null)
					return false;
			} else if (!fName.equals(other.fName))
				return false;
			if (lName == null) {
				if(other.lName != null)
					return false;
			} else if (!lName.equals(other.lName))
				return false;
			return true;
		}

		@Override
		public String toString() {
			
			return "Employee("+ 
			"firstNsme= " + fName + "\'" +
			", lastName= " + lName + "\'" +
			", manager_id=" + managerId + "\'" +
			", dept_id=" + deptId + "\'" +
			", employee_id" + employeeId + 
			"}";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + employeeId;
			result = prime * result + managerId;
			result = prime * result + deptId;
			result = prime * result + ((fName == null)? 0 : fName.hashCode());
			result = prime * result + ((lName ==null) ? 0 : lName.hashCode());
			return result;
		}
		
		
	

}
