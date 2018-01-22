package com.nagarro.assignment4.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.assignment4.data.Constants;
import com.nagarro.assignment4.data.Image;
import com.nagarro.assignment4.services.Util;

/**
 * Servlet implementation class CurrentUserData
 */
public class CurrentUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CurrentUserData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = (Integer) request.getAttribute(Constants.UserId.getName());

		HttpSession websession = request.getSession();
		Util.Singleton.getInstance().setWebSession(websession);
		websession.setAttribute(Constants.UserId.getName(), id);
		List<Image> images = Util.imgOperation.getImages(id);
		websession.setAttribute(Constants.ImageList.getName(), images);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.ImageUploadPath.getName());
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
