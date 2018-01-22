package com.nagarro.testrunner.response.persistence.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.request.dto.EmailDO;
import com.nagarro.testrunner.service.EmailService;
import com.nagarro.testrunner.utils.BarChartGenerator;

@Component
public class SendEmailImpl implements SendEmail {

	@Value("${mail.username}")
	private String user;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.smtp.starttls.enable}")
	private String mailEnable;

	@Value("${mail.smtp.port}")
	private String port;

	@Value("${mail.smtp.host}")
	private String host;

	@Value("${mail.smtp.auth}")
	private String auth;
	
	@Autowired
	EmailService emailService;
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailEnable() {
		return mailEnable;
	}

	public void setMailEnable(String mailEnable) {
		this.mailEnable = mailEnable;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public void notificationWithAttachment(List<EmailDO> emailList, String reportLocation, String content,String htmlFromReport,byte[] imageBytes)  {
		try {
			//emailService.sendSimpleMail("bansal", "himanshu.bansal@nagarro.com");
			
			int i=0;
			String recipient;
			//List<EmailDO> emailsListCopy = emailList;
			List<EmailDO> valuesToRemove = new ArrayList<>();
			for(EmailDO email:emailList){
				recipient=email.getRecipient();
				if(recipient==null || recipient.isEmpty()){
					valuesToRemove.add(email);
				}
				
				System.out.println("Email IS::::::"+email.getRecipient());
			}
			emailList.removeAll(valuesToRemove);
			String [] emails=new String[emailList.size()];
			for(EmailDO email:emailList){
				emails[i++]=email.getRecipient();
			}
			emailService.sendMailWithAttachment(emails,reportLocation, content, htmlFromReport,imageBytes);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Method to send email to  addresses from list of emails with the attached testing report 
	 */
//	@Override
//	public void notificationWithAttachment(List<EmailDO> emails, String reportLocation, String content) {
//		final String user = getUser();
//		final String password = getPassword();
//
//		// Get the session object
//		Properties props = new Properties();
//		props.put("mail.smtp.starttls.enable", getMailEnable());
//		props.put("mail.smtp.port", getPort());
//		props.put("mail.smtp.host", getHost());
//		props.put("mail.smtp.auth", getAuth());
//		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user, password);
//			}
//		});
//
//		for (EmailDO email : emails) {
//
//			// Compose the message
//			try {
//				MimeMessage message = new MimeMessage(session);
//				message.setFrom(new InternetAddress(user));
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
//				message.setSubject(email.getSubject());
//				// Create the message part
//				BodyPart messageBodyPart = new MimeBodyPart();
//
//				// Now set the actual message
//				messageBodyPart.setText(content);
//
//				// Create a multipart message
//				Multipart multipart = new MimeMultipart();
//
//				// Set text message part
//				multipart.addBodyPart(messageBodyPart);
//
//				// Part two is attachment
//				messageBodyPart = new MimeBodyPart();
//				DataSource source = new FileDataSource(reportLocation);
//				messageBodyPart.setDataHandler(new DataHandler(source));
//				messageBodyPart.setFileName(source.getName());
//				multipart.addBodyPart(messageBodyPart);
//
//				// Send the complete message parts
//				message.setContent(multipart);
//				
//				// send the message
//				Transport.send(message);
//
//				System.out.println("message sent successfully...");
//
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
}