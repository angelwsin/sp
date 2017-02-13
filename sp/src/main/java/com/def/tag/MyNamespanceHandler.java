package com.def.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespanceHandler extends NamespaceHandlerSupport {

	public void init() {
		
		registerBeanDefinitionParser("user", new UserBeanDefineParse());
	}

}
