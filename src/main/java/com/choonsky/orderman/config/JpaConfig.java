package com.choonsky.orderman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.choonsky.orderman.repository")
public class JpaConfig {
}