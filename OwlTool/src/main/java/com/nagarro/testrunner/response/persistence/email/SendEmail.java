package com.nagarro.testrunner.response.persistence.email;

import java.util.List;

import com.nagarro.testrunner.request.dto.EmailDO;

public interface SendEmail {

	/**
	 * 
	 * @param emailList
	 * @param reportLocation
	 * @param content
	 * @param htmlFromReport
	 * @param imageBytes
	 */
	void notificationWithAttachment(List<EmailDO> emailList, String reportLocation, String content,
			String htmlFromReport, byte[] imageBytes);

}