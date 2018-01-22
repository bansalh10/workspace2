package com.nagarro.assignment4.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.assignment4.data.Constants;
import com.nagarro.assignment4.services.Util;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String username = request.getParameter(Constants.Username.getName());
		String password = request.getParameter(Constants.Password.getName());
		int validUserId = Util.userOperation.validatedUserId(username, password);
		if (validUserId == -1) {
			System.out.println("Invalid username and password");
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(Constants.LoginPagePath.getName());
			response = resp;
		} else {
			request.setAttribute(Constants.UserId.getName(), validUserId);
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
