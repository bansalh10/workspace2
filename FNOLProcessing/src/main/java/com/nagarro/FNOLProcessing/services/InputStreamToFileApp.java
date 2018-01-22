package com.nagarro.FNOLProcessing.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputStreamToFileApp {
//public static 	int i;
private static InputStreamToFileApp instance=null;
public static InputStreamToFileApp getInstance() {
	if(instance==null){
		instance=new InputStreamToFileApp();
	}
	return instance;
}
public InputStreamToFileApp(){
//	i=getMaxId();
}

    public synchronized void convert(InputStream file,int i) {

	OutputStream outputStream = null;
    
	try {
		// write the inputStream to a FileOutputStream
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		outputStream =
                    new FileOutputStream(new File("C:\\Users\\himanshubansal\\Desktop\\Ui Assignment\\Pdfs\\FNOL_"+df.format(date)+"_"+ i +".pdf"));

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

	public int getMaxId(){
		return FNOLDao.getFnolDao().getMaxId();
		
	}

	
}
