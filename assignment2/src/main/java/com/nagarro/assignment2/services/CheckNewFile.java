package com.nagarro.assignment2.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.nagarro.assignment2.data.Constants;
import com.nagarro.assignment2.flightSystem.Instances;

public class CheckNewFile {
	Timer timer = new Timer();
	BasicFileAttributes view = null;
	Date currDate, modifyDate;
	Readflightdata readflight;
	long timeperiod = Constants.getTimerPeriod();

	public void loadNewfile() {
		readflight = Instances.readflightInstance();

		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("in timer");
				modifyDate = new Date(Constants.directory.lastModified());
				currDate = new Date();
				if ((currDate.getTime() - modifyDate.getTime()) < timeperiod) {
					System.out.println("file modified");
					for (File file : Constants.directory.listFiles()) {

						try {
							view = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if ((currDate.getTime() - view.creationTime().toMillis()) < timeperiod) {
							System.out.println("file created");
							readflight.setFileData(file);
						}
					}
				}

			}
		}, timeperiod, timeperiod);
	}

	public void stopTimerThread() {
		timer.cancel();
	}
}
