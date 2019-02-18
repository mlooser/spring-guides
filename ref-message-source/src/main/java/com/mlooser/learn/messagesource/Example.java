package com.mlooser.learn.messagesource;

import org.springframework.context.MessageSource;

public class Example {
	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void execute() {
		String msgString = messageSource.getMessage("argument.required", new Object[] {"faith"},"<Missing>",null);
		System.out.println(msgString);
	}
}
