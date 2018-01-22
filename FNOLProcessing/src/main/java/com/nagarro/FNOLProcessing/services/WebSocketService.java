package com.nagarro.FNOLProcessing.services;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.nagarro.FNOLProcessing.data.MaintainSessions;
@ServerEndpoint("/message")
public class WebSocketService {
	
MaintainSessions maintainSessions=new MaintainSessions();
	    @OnOpen
	        public void open(Session session) {
	    	System.out.println("WebSocket opened");
	    	maintainSessions.setSession(session);
	    	
	    }

	    @OnClose
	        public void close(Session session) {
	    }

	    @OnError
	        public void onError(Throwable error) {
	    	System.out.println("Error");
	    }

	    
	        
	}  

