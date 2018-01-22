package com.nagarro.core.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.core.data.FNOLdata;
import com.nagarro.core.services.Util;

/**
 * Servlet implementation class DeleteImgServlet
 */
public class SearchFnol extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchFnol() {
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
		HttpSession websession = request.getSession();
		
		String name=request.getParameter("name");
		String sDate=request.getParameter("date");
		
		Date date = null;
		if(sDate!=null && sDate!=""){
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(date==null && (name==null || name=="")){
			response.sendRedirect("fnols");
		}
		else{
		List<FNOLdata> fnols = Util.fnolDao.getFnolsByName(name,date);
		websession.setAttribute("fnolList", fnols);
			request.getRequestDispatcher("/WEB-INF/fnolSearch.jsp").forward(request, response);
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
