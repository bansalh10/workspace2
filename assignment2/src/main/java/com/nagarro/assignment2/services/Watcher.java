package com.nagarro.assignment2.services;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

import com.nagarro.assignment2.data.Constants;
import com.nagarro.assignment2.data.FlightDataDs;

import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Watcher extends Thread {

	@Override
	public void run() {
		// super.run();
		Path dir = Paths.get("C:/Users/himanshubansal/neon/workspace/assignment2/src/main/java/resources");
		WatchService watcher = null;
		WatchKey key = null;
		boolean status;
		try {
			watcher = FileSystems.getDefault().newWatchService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			status = false;
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				//
				// Path dirPath = (Path)key.watchable();
				// Path fullPath = dirPath.resolve((Path) event.context());
				//
				// System.out.println(fullPath.getFileName().toString());

				if (kind == StandardWatchEventKinds.ENTRY_CREATE || kind == StandardWatchEventKinds.ENTRY_MODIFY
						|| kind == StandardWatchEventKinds.ENTRY_DELETE) {
					System.out.println("in create");
					status = true;
					System.out.println("we did with creation");
				}

			}
			if (status) {
				Constants.setMap().clear();
				ReadAllFiles readallfiles = new ReadAllFiles();
				readallfiles.run();
			}
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}

	}

}
