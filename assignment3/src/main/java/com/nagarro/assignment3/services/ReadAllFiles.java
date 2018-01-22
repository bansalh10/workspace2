package com.nagarro.assignment3.services;

import java.io.File;

import com.nagarro.assignment3.data.Constants;

public class ReadAllFiles {
	private static ReadAllFiles readallfiles = null;

	public static ReadAllFiles getReadallfiles() {
		if (readallfiles == null) {
			readallfiles = new ReadAllFiles();
		}
		return readallfiles;
	}

	public void run() {
		for (File file : Constants.directory.listFiles()) {
			Readflightdata.getReadflight().getFileData(file);
		}
	}
}
