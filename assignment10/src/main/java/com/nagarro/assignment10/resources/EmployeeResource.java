package com.nagarro.assignment10.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nagarro.assignment10.data.Product;
import com.nagarro.assignment10.services.EmployeeDao;
import com.nagarro.assignment10.services.UserDao;

/**
 * Root resource (exposed at "employees" path)
 */
@Path("employees")
public class EmployeeResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "json" media type.
	 *
	 * @return Response that will be returned as a json response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		// System.out.println(userId + password);
		Response response;

		List<Product> employees = EmployeeDao.getOperation().getEmployess();

		response = Response.ok().entity(employees.toArray(new Product[employees.size()])).build();

		return response;
	}

	@Path("employee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Product employee) {
		Response response;
		boolean added = EmployeeDao.getOperation().addEmployee(employee);
		if (added) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{Id}")
	public Response getEmployeeById(@PathParam("Id") String employeeCode) {
		System.out.println("fdass");
		Response response;
		if (employeeCode == null) {
			response = Response.status(Status.BAD_REQUEST).build();
		} else {
			Product employee = EmployeeDao.getOperation().getEmployeeById(employeeCode);
			if (employee == null) {
				response = Response.status(Status.NOT_FOUND).build();
			} else {
				response = Response.ok().entity(employee).build();
			}
		}
		return response;
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Product employee) {
		Response response;
		boolean updated = EmployeeDao.getOperation().updateEmployee(employee);
		if (updated) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		// redirect to second page
		return response;

	}
}
