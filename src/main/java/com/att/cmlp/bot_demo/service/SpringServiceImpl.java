package com.att.cmlp.bot_demo.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.att.ajsc.common.Tracable;

import com.att.cmlp.bot_demo.model.HelloWorld;

@Service
public class SpringServiceImpl implements SpringService {
	public static Logger log = Logger.getLogger(SpringServiceImpl.class);
	public SpringServiceImpl() {
		// needed for instantiation
	}

	@Override
	@Tracable(message="invoking quick hello service ")
	public HelloWorld getQuickHello(String name) {
		log.info("Say a quick hello");
		log.debug("Say a quick hello for '" + name + "'");
		if (name == null || name.isEmpty()) {
			name = "world";
		}
		String message = "Hello " + name + "!";
		log.debug("Say hello message: " + message);
		HelloWorld hello = new HelloWorld(message);
		log.debug("Say hello message object: " + hello);
		return hello;
	}
}