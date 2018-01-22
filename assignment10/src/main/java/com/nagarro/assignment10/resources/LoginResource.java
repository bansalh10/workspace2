package com.nagarro.assignment10.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nagarro.assignment10.data.User;
import com.nagarro.assignment10.services.UserDao;

@Path("login")
public class LoginResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(User user) {
		Response response;
		System.out.println(user.getUserId() + user.getPassword());
		String username = UserDao.getUserDao().getvalidUsername(user.getUserId(), user.getPassword());
		if (username == null) {
			response = Response.status(Status.UNAUTHORIZED).build();
		} else {
			user.setName(username);
			response = Response.ok().entity(user).build();
		}
		return response;
	}
}
