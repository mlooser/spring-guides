package com.mlooser.learn.messagesource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		String message = ctx.getMessage("message", null, "Default", null);
		System.out.println(message);
		
		Example eBean = ctx.getBean("example",Example.class);
		eBean.execute();
				
	}

}
