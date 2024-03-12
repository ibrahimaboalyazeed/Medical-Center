package com.global.medical.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableCaching
public class WebConfig implements WebMvcConfigurer{
	
	String [] ALLOWED_ORIGINS = {"http://localhost:4200", "https://google.com"};
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(ALLOWED_ORIGINS) // ("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
	
	
	@Bean
	public AuditorAware<String> auditorAware() {
		
		return new AuditorAwareImpl();
		
		}
}
