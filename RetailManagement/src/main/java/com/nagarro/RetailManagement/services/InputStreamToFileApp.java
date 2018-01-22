package com.nagarro.RetailManagement.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamToFileApp {
    public void convert(InputStream file) {

	OutputStream outputStream = null;

	try {
		// write the inputStream to a FileOutputStream
		outputStream =
                    new FileOutputStream(new File("C:\\Users\\himanshubansal\\Desktop\\Ui Assignment\\Pdfs\\a.pdf"));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = file.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}

		System.out.println("Done!");

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (file != null) {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (outputStream != null) {
			try {
				// outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
    }
}
