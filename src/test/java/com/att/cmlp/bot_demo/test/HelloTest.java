package com.att.cmlp.bot_demo.test;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.att.ajsc.common.RequestScopeModifier;

import com.att.cmlp.bot_demo.Application;
import com.att.cmlp.bot_demo.model.HelloWorld;
import com.att.cmlp.bot_demo.service.rs.RestService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class, RequestScopeModifier.class })
@WebAppConfiguration
@IntegrationTest({"server.port=0", "spring.profiles.active=test"})
public class HelloTest {

	@Autowired
	RestService service;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQuickHello() throws Exception {
		HelloWorld helloWorld = service.getQuickHello("test");
		assertEquals("Hello test!", helloWorld.getMessage());
	}
}
