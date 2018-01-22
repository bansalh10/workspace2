package com.nagarro.core.services;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.core.daos.FNOLDao;

/**
 * This class provides instances of objects used.
 * 
 * @author himanshubansal
 *
 */
public class Util {
	public static FNOLDao fnolDao = new FNOLDao();
	

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
