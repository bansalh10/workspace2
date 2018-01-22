package com.nagarro.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.nagarro.service.ICheckNewFile;
import com.nagarro.service.IReadAllFiles;

@Component
public class StartEventHandeler implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IReadAllFiles readfiles;
	@Autowired
	private ICheckNewFile checker;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		readfiles.run();
		checker.loadNewfile();

	}

}
