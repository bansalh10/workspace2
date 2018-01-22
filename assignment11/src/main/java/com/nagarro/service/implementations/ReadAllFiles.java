package com.nagarro.service.implementations;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.Constants.Constants;
import com.nagarro.service.IReadAllFiles;
import com.nagarro.service.IReadFlightData;

@Service
public class ReadAllFiles implements IReadAllFiles {
	@Autowired
	IReadFlightData readFlightData;
	private static ReadAllFiles readallfiles = null;

	public static ReadAllFiles getReadallfiles() {
		if (readallfiles == null) {
			readallfiles = new ReadAllFiles();
		}
		return readallfiles;
	}

	public void run() {
		for (File file : Constants.directory.listFiles()) {
			readFlightData.getFileData(file);
		}
	}
}
