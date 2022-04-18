package com.revature.data;

public class DAOFactory {

	private static EmployeeDAO employeeDAO = null;
	private static DepartmentDAO departmentDAO = null;
	private static EventDAO eventDAO = null;
	private static RequestDAO requestDAO = null;
	private static StatusDAO statusDAO = null;
	
	private DAOFactory() {
		
	}
	
	public static EmployeeDAO getEmployeeDAO() {
		if(employeeDAO == null) {
			employeeDAO = new EmployeeDAOImp();
		}
		return employeeDAO;
	}
	
	public static DepartmentDAO getDepartmentDAO() {
		if(departmentDAO == null) {
			departmentDAO = new DepartmentDAOImp();
		}
		return departmentDAO;
	}
	
	public static EventDAO getEventDAO() {
		if(eventDAO == null) {
			eventDAO = new EventDAOImp();
		}
		return eventDAO;
	}
	
	public static RequestDAO getRequestDAO() {
		if(requestDAO == null) {
			requestDAO = new RequestDAOImp();
		}
		return requestDAO;
	}
	
	public static StatusDAO getStatusDAO() {
		if(statusDAO == null) {
			statusDAO = new StatusDAOImp();
		}
		return statusDAO;
		}
	
}
