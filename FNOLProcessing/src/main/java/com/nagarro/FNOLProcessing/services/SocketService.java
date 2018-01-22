package com.nagarro.FNOLProcessing.services;

import java.net.URISyntaxException;

import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketService {
	private SocketService instance = null;
	
   
	
	public Socket socket;

	public SocketService() {
		try {
			 
			 String ioserver = "http://localhost:5000";
			IO.Options options = new IO.Options();
			options.forceNew = true;
			System.out.println(ioserver);
			socket = IO.socket(ioserver, options);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configureSocket();
	}

	public SocketService getInstance() {
		if (instance == null) {
			instance = new SocketService();
		}
		return instance;
	}

	public void configureSocket() {
		try {

			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					System.out.println("PASS HERE");

				}

			}).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					System.out.println("connect error");
				}

			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

				@Override
				public void call(Object... args) {
					System.out.println("PASS HEREGG");
				}

			});
			socket.open();
			System.out.println("Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int passMessage(String text) {
		try {
			JSONObject parms = new JSONObject();
			parms.put("msg", text);
			socket.emit("message", parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}