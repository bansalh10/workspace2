package com.nagarro.assignment4.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.assignment4.data.Constants;
import com.nagarro.assignment4.data.User;
import com.nagarro.assignment4.services.Util;

/**
 * Servlet implementation class ImgUploadServlet
 */
public class ImgUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImgUploadServlet() {
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
		int id = (Integer) websession.getAttribute(Constants.UserId.getName());
		ServletFileUpload upload = Util.getUploader();
		User user = Util.userOperation.getUser(id);
		try {
			@SuppressWarnings("unchecked")
			List<File> fileitems = upload.parseRequest(request);
			FileItem fi = (FileItem) fileitems.get(0);
			if (fi.getSize() != 0) {
				if (Util.imgOperation.saveImage(user, fi)) {
					websession.setAttribute(Constants.ImageList.getName(), Util.imgOperation.getImages(id));
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(Constants.ImageUploadPath.getName());
					dispatcher.forward(request, response);
				} else {

					response.setContentType("text/html");
					response.getWriter().write(
							"<script type=\"text/javascript\">alert('Cannot add the Image because image size may be greater than 1 MB');</script>");

				}
			}
			getServletContext().getRequestDispatcher(Constants.ImageUploadPath.getName()).include(request, response);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
