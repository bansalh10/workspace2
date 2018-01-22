package com.nagarro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nagarro.config.WebConfig;
import com.nagarro.service.implementations.ReadAllFiles;

//@SpringBootApplication(exclude = { DispatcherServletAutoConfiguration.class })
//(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
public class Assignment11Application {

	public static void main(String[] args) {

		// AnnotationConfigWebApplicationContext context=new
		// AnnotationConfigWebApplicationContext();
		// context.register(WebConfig.class);
		//
		ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

		ReadAllFiles readfiles = context.getBean(ReadAllFiles.class);
		readfiles.run();
		System.out.println("scdscsd");

	}

	// @Bean
	// public ServletRegistrationBean servletRegistrationBean(){
	// return new ServletRegistrationBean(new DispatcherServlet(),"*.html");
	// }
}
