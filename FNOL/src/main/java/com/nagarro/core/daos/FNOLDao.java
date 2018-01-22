package com.nagarro.core.daos;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.nagarro.core.data.FNOLdata;
import com.nagarro.core.services.HibernateUtilities;

public class FNOLDao {

	public List<FNOLdata> getFnolsByName(final String name,final Date date) {
		String query = null;
		String sDate = null;
		if(date!=null){
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			 sDate = df.format(date);
		}
		
		if(date==null){
			query="select fnol from FNOLdata as fnol where fnol.name like '%"+name+"%' ";
		}
		else if(name==null || name==""){
			query="select fnol from FNOLdata as fnol where fnol.date='"+sDate+"'";
		}
		else{
			query="select fnol from FNOLdata as fnol where fnol.name like '%"+name+"%' and fnol.date='"+sDate+"'";
		}
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		List<FNOLdata> fnols = session.createQuery(query)
				.getResultList();

		session.getTransaction().commit();
		session.close();
		return fnols;

	}

	public List<FNOLdata> getFnols() {
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		 
		List<FNOLdata> fnols = session.createQuery("select fnol from FNOLdata as fnol").getResultList();

		session.getTransaction().commit();
		session.close();
		return fnols;

	}

}
