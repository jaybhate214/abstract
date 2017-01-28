package com.att.cmlp.bot_demo.service.rs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.ajsc.common.Tracable;

import com.att.cmlp.bot_demo.model.HelloWorld;
import com.att.cmlp.bot_demo.service.SpringService;

@Controller
public class RestServiceImpl implements RestService {
	private static Logger log = Logger.getLogger(RestServiceImpl.class);

	@Autowired
	private SpringService service;
	
	public RestServiceImpl() {
		// needed for autowiring
	}

	@Override
	@Tracable(message="invoking quick hello")
	public HelloWorld getQuickHello(String name) {
		log.info("Get a quick hello");
		log.debug("Get a quick hello for " + name);
		return service.getQuickHello(name);
	}
}
