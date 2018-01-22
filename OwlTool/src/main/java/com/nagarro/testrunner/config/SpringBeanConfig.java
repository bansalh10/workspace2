/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.nagarro.testrunner.utils.FileNameWrapper;

/**
 * 
 * @author anujmehra
 *
 */
@Configuration
@ComponentScan(basePackages = "com.nagarro.testrunner")
@PropertySource("file:resources/environment.properties")
public class SpringBeanConfig implements ApplicationContextAware, EnvironmentAware {

	public static final String CHARACTER_ENCODING = "UTF-8";

	private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";
	private static final String HOST = "mail.smtp.host";
	private static final String PORT = "mail.smtp.port";
	private static final String PROTOCOL = "mail.server.protocol";
	private static final String USERNAME = "mail.username";
	private static final String PASSWORD = "mail.password";

	private Environment environment;
	private ApplicationContext applicationContext;

	@Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;

	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public FileNameWrapper inputTestCaseFile() {
		return new FileNameWrapper();
	}

	@Bean
	public JavaMailSender mailSender() throws IOException {

		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// Basic mail sender configuration, based on emailconfig.properties
		mailSender.setHost(this.environment.getProperty(HOST));
		mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT)));
		mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
		mailSender.setUsername(this.environment.getProperty(USERNAME));
		mailSender.setPassword(this.environment.getProperty(PASSWORD));

		// JavaMail-specific mail sender configuration, based on
		// javamail.properties
		final Properties javaMailProperties = new Properties();
		javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;

	}

	@Bean
	public TemplateEngine emailTemplateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

		// Resolver for HTML emails (except the editable one)
		templateEngine.addTemplateResolver(htmlTemplateResolver());
		return templateEngine;
	}

	private ITemplateResolver htmlTemplateResolver() {
		final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setCheckExistence(true);
		 templateResolver.setOrder(Integer.valueOf(1));
		templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
		templateResolver.setPrefix("/mail/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding(CHARACTER_ENCODING);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	

}
