package com.att.cmlp.bot_demo;

import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.att.ajsc.common.messaging.DateTimeParamConverterProvider;
import com.att.ajsc.common.messaging.LogRequestFilter;
import com.att.ajsc.common.messaging.ObjectMapperContextResolverNonNull;
import com.att.ajsc.common.messaging.TransactionIdResponseFilter;
import com.att.ajsc.common.messaging.TransactionIdRequestFilter;
import com.att.cmlp.bot_demo.service.rs.RestServiceImpl;

@Component
@ApplicationPath("/")
public class JerseyConfiguration extends ResourceConfig {
	private static final Logger log = Logger.getLogger(JerseyConfiguration.class.getName());
	
	@Autowired
    public JerseyConfiguration(LogRequestFilter lrf) {
    	register(new ObjectMapperContextResolverNonNull());
        register(RestServiceImpl.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        register(TransactionIdRequestFilter.class, 6000);
        register(TransactionIdResponseFilter.class, 6001);
        register(DateTimeParamConverterProvider.class);
        register(lrf, 6002);
        register(new LoggingFilter(log, true));
    }

	@Bean
	public Client jerseyClient() {
		return ClientBuilder.newClient(
				new ClientConfig()
				.register(TransactionIdRequestFilter.class)
				.register(TransactionIdResponseFilter.class)
				.register(DateTimeParamConverterProvider.class));
	}
}