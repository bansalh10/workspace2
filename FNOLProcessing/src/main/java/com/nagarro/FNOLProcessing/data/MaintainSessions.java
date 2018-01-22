package com.nagarro.FNOLProcessing.data;

import javax.websocket.Session;

import com.nagarro.FNOLProcessing.services.FNOLDao;

public class MaintainSessions {
	public static Session session;
	public static int id;
    public MaintainSessions(){
    	id=FNOLDao.getFnolDao().getMaxId();
    }
public Session getSession() {
	return session;
}

public void setSession(Session session) {
	this.session = session;
}
}
