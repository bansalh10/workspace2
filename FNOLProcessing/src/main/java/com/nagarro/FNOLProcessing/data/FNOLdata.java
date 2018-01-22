package com.nagarro.FNOLProcessing.data;

public class FNOLdata {
	

	private int id;
	private String fnolId;
	private String name;
	private String email;
	private String date;
	private String time;
	private String ssn;
	private String happinessIndex;
	private String creditScoreVariation;
    private int isFraud;
   
public FNOLdata(){
    	
    }
public FNOLdata(int id, String fnolId, String name, String email, String date, String time, String ssn,
		String happinessIndex, String creditScoreVariation, int isFraud) {
	super();
	this.id = id;
	this.fnolId = fnolId;
	this.name = name;
	this.email = email;
	this.date = date;
	this.time = time;
	this.ssn = ssn;
	this.happinessIndex = happinessIndex;
	this.creditScoreVariation = creditScoreVariation;
	this.isFraud = isFraud;
}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFnolId() {
		return fnolId;
	}

	public void setFnolId(String fnolId) {
		this.fnolId = fnolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getHappinessIndex() {
		return happinessIndex;
	}

	public void setHappinessIndex(String happinessIndex) {
		this.happinessIndex = happinessIndex;
	}

	public String getCreditScoreVariation() {
		return creditScoreVariation;
	}

	public void setCreditScoreVariation(String creditScoreVariation) {
		this.creditScoreVariation = creditScoreVariation;
	}

	public int getIsFraud() {
		return isFraud;
	}

	public void setIsFraud(int isFraud) {
		this.isFraud = isFraud;
	}
}
