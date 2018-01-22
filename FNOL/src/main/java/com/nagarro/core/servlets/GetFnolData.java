package com.nagarro.core.servlets;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.core.data.Constants;
import com.nagarro.core.data.FNOLdata;
import com.nagarro.core.services.Util;

/**
 * Servlet implementation class CurrentUserData
 */
public class GetFnolData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFnolData() {
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

		//int id = (Integer) request.getAttribute(Constants.UserId.getName());

		HttpSession websession = request.getSession();
		Util.Singleton.getInstance().setWebSession(websession);
		List<FNOLdata> fnols = Util.fnolDao.getFnols();
		websession.setAttribute("fnolList", fnols);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/fnolSearch.jsp");
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
