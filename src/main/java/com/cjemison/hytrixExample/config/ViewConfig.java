package com.cjemison.hytrixExample.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by cjemison on 6/5/16.
 */
@Configuration
@EnableWebMvc
@EnableCircuitBreaker
public class ViewConfig {
}
