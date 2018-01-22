package com.nagarro.assignment4.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.assignment4.data.Constants;
import com.nagarro.assignment4.services.Util;

/**
 * Servlet implementation class UpdateImgServlet
 */
public class UpdateImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateImgServlet() {
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
		HttpSession websession = request.getSession(false);
		if (websession != null) {
			String imgname = request.getParameter(Constants.Updatedname.getName());
			int imageid = Integer.parseInt(request.getParameter(Constants.ImageId.getName()));
			Util.imgOperation.updateImage(imgname, imageid);
			int userid = (Integer) websession.getAttribute(Constants.UserId.getName());
			websession.setAttribute(Constants.ImageList.getName(), Util.imgOperation.getImages(userid));
			request.getRequestDispatcher(Constants.ImageUploadPath.getName()).forward(request, response);
		} else {
			response.sendRedirect(Constants.LoginPagePath.getName());
		}
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
