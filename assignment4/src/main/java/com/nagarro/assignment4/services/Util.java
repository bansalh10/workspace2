package com.nagarro.assignment4.services;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.assignment4.daos.ImageDao;
import com.nagarro.assignment4.daos.UserDao;

/**
 * This class provides instances of objects used.
 * 
 * @author himanshubansal
 *
 */
public class Util {
	public static ImageDao imgOperation = new ImageDao();
	public static UserDao userOperation = new UserDao();

	public static ServletFileUpload getUploader() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		return upload;

	}

	public static class Singleton {

		private static Singleton singleton = new Singleton();

		/*
		 * A private Constructor prevents any other class from instantiating.
		 */
		private Singleton() {
		}

		private static HttpSession webSession;

		/* Static 'instance' method */
		public static Singleton getInstance() {
			return singleton;
		}

		/* Other methods protected by singleton-ness */

		public static HttpSession getWebSession() {
			return webSession;
		}

		public static void setWebSession(HttpSession webSession) {
			Singleton.webSession = webSession;
		}
	}
}
