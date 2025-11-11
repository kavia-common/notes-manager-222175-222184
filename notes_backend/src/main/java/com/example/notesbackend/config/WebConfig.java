package com.example.notesbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * PUBLIC_INTERFACE
 * Enables Spring Data web support for Pageable and Sort argument resolvers.
 */
@Configuration
@EnableSpringDataWebSupport
public class WebConfig {
}
