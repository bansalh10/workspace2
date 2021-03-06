package com.nagarro.service.implementations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.Constants.Constants;
import com.nagarro.service.ICheckNewFile;
import com.nagarro.service.IReadFlightData;

/**
 * This class checks new file in the target folder and load it in db.
 * 
 * @author himanshubansal
 *
 */
@Service
public class CheckNewFile implements ICheckNewFile {
	private static CheckNewFile checkfile = null;
	Timer timer = new Timer();
	long timeperiod = Constants.timerPeriod;
	@Autowired
	IReadFlightData readFlightData;

	public static CheckNewFile getCheckfile() {
		if (checkfile == null) {
			checkfile = new CheckNewFile();
		}
		return checkfile;
	}

	/**
	 * load new files if any in db.Starts a timer which checks new files.
	 */
	public void loadNewfile() {
		timer.scheduleAtFixedRate(new TimerTask() {
			BasicFileAttributes view = null;
			Date currDate, modifyDate;

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
							readFlightData.getFileData(file);
						}
					}
				}

			}
		}, timeperiod, timeperiod);
	}

	/**
	 * stop timer
	 */
	public void stopTimerThread() {
		timer.cancel();
	}
}
