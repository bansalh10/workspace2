package com.nagarro.testrunner.service;

import java.util.Locale;

import javax.mail.MessagingException;

public interface EmailService {

	/* 
	 * Send plain TEXT mail 
	 */
	void sendTextMail(String recipientName, String recipientEmail, Locale locale) throws MessagingException;

	/* 
	 * Send HTML mail (simple) 
	 */
	void sendSimpleMail(String recipientName, String recipientEmail) throws MessagingException;

	

	/* 
	 * Send HTML mail with inline image
	 */
	void sendMailWithInline(String recipientName, String recipientEmail, String imageResourceName, byte[] imageBytes,
			String imageContentType, Locale locale) throws MessagingException;

	/*
	 * Send HTML mail with inline image
	 */
	void sendEditableMail(String recipientName, String recipientEmail, String htmlContent, Locale locale)
			throws MessagingException;
	
	/* 
	 * Send HTML mail with attachment. 
	 */
	void sendMailWithAttachment(String[] emails, String fileLocation, String content, String htmlFromReport,
			byte[] imageBytes) throws MessagingException;

}