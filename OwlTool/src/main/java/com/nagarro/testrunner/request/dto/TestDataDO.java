/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.request.dto;

/**
 * 
 */
import java.util.List;

/**
 * This class is a master repository for the input excel file.
 * 
 * @author anujmehra
 *
 */
public class TestDataDO {

	/** The environment details. */
	private EnvironmentDetailsDO environmentDetails;
	
	/** The test suites. */
	private ResourcesDO resource;
	
	/** The emails. */
	private List<EmailDO> emails;

	
	/**
	 * @return the environmentDetails
	 */
	public EnvironmentDetailsDO getEnvironmentDetails() {
		return environmentDetails;
	}

	/**
	 * @param environmentDetails the environmentDetails to set
	 */
	public void setEnvironmentDetails(EnvironmentDetailsDO environmentDetails) {
		this.environmentDetails = environmentDetails;
	}

	
	/**
	 * @return the resource
	 */
	public ResourcesDO getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(ResourcesDO resource) {
		this.resource = resource;
	}

	/**
	 * @return the emails
	 */
	public List<EmailDO> getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(List<EmailDO> emails) {
		this.emails = emails;
	}


	

}
