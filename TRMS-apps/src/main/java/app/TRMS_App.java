package app;

import com.revature.data.DAOFactory;
import com.revature.data.DepartmentDAOImp;
import com.revature.data.EmployeeDAO;
import com.revature.data.RequestDAOImp;
import com.revature.data.EventDAOImp;
import com.revature.data.StatusDAOImp;

import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Event;
import com.revature.model.Request;
import com.revature.model.Status;
import java.util.Map;

//import services.ConnectionFactory;
import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import services.EmployeeService;
import services.EmployeeServiceImp;



public class TRMS_App {

	private static EmployeeService empServ = new EmployeeServiceImp(); 
		
	
	public static void main(String args[]) {
		Javalin app = Javalin.create(config ->{
			config.enableCorsForAllOrigins();	
		
		});
		
		app.start(8088);
		
		app.get("/emp", ctx ->{
			Employee employee = ctx.bodyAsClass(com.revature.model.Employee.class);
			ctx.json(employee.getEmployeeId());
			ctx.json(employee.getfName());
			ctx.json(employee.getlName());
			ctx.json(employee.getDeptId());
			ctx.json(employee.getManagerId());
			System.out.println(employee);
			ctx.result(employee.toString());
		});
		
	
		/*app.get("emp/service", ctx ->{
			ctx.json(EmployeeService.class);
		}); */
		
		app.get("/emp/{id}", ctx->{
			Employee employee =  ctx.bodyAsClass(com.revature.model.Employee.class);
			ctx.json(employee);
		});
		
		app.post("/emp/create", ctx->{
			Employee employee = ctx.bodyAsClass(com.revature.model.Employee.class);
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			
			int employee_id =employeeDAO.create(employee);
			
			ctx.result("the user id is " + employee_id);
		});
		
		app.post("/login", ctx -> {
			Map<String, String> credentials = ctx.bodyAsClass(Map.class);
			String fName = credentials.get("fName");
			System.out.println(fName);
			String lName = credentials.get("lName");
			System.out.println(lName);
			
				Employee employee = empServ.logIn(fName, lName);
				if(employee != null) {
				ctx.json(employee);
				}else {
				
				ctx.status(HttpCode.UNAUTHORIZED);
			}
		});

		
		app.put("emp/update", ctx->{
			Employee employee =ctx.bodyAsClass(com.revature.model.Employee.class);
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			employeeDAO.update(employee);
		});
		
		app.get("/dept", ctx-> {
			Department department = ctx.bodyAsClass(com.revature.model.Department.class);
			ctx.json(department.getDeptId());
			ctx.json(department.getDeptHeadId());
			ctx.json(department.getDeptName());
			ctx.result(department.toString());
		});
		
		app.post("/dept/create", ctx -> {
            try{
                Department department = ctx.bodyAsClass(com.revature.model.Department.class);
                DepartmentDAOImp departmentDAO = new DepartmentDAOImp();
                departmentDAO.create(department);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
		
		app.get("/request", ctx -> {
            Request request = ctx.bodyAsClass(com.revature.model.Request.class);
            ctx.json(request.getRequestId());
            ctx.json(request.getSubmitterId());
            ctx.json(request.getEventId());
            ctx.json(request.getStatusId());
            ctx.json(request.getCost());
            ctx.json(request.getDescription());
            ctx.json(request.getLocation());
            ctx.json(request.getEventDate());
            
            ctx.result(request.toString());
        });
		
		app.post("/request/create", ctx -> {
            try{
                Request reimbursement_request = ctx.bodyAsClass(com.revature.model.Request.class);
                RequestDAOImp requestDAO = new RequestDAOImp();
                requestDAO.create(reimbursement_request);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

		app.get("/status", ctx -> {
            Status status =ctx.bodyAsClass(com.revature.model.Status.class);
            ctx.json(status.getStatId());
            ctx.json(status.getStatName());
            ctx.result(status.toString());
        });


        app.post("/status/create", ctx -> {
            try{
                Status status = ctx.bodyAsClass(com.revature.model.Status.class);
                StatusDAOImp statusDAO = new StatusDAOImp();
                statusDAO.create(status);
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        
        app.get("/eventType", ctx -> {
            Event event = ctx.bodyAsClass(com.revature.model.Event.class);
            ctx.json(event.getEventId());
            ctx.json(event.getEventName());
            ctx.result(event.toString());
        });


        app.post("/eventType/create", ctx -> {
            try{
                Event event = ctx.bodyAsClass(com.revature.model.Event.class);
                EventDAOImp eventDAO = new EventDAOImp();
                eventDAO.create(event);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
		
	}
}
