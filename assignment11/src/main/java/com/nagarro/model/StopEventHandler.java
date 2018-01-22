package com.nagarro.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import com.nagarro.service.ICheckNewFile;
import com.nagarro.service.IFlightService;

@Component
public class StopEventHandler implements ApplicationListener<ContextClosedEvent> {

	@Autowired
	private ICheckNewFile checker;
	@Autowired
	private IFlightService flightService;

	@Override
	public void onApplicationEvent(ContextClosedEvent arg0) {
		checker.stopTimerThread();
		flightService.cleanDb();
	}

}
