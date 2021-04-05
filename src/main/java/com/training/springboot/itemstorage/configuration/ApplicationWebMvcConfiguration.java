package com.training.springboot.itemstorage.configuration;

import com.training.springboot.itemstorage.utils.interceptor.LoggingHandler;
import com.training.springboot.itemstorage.utils.interceptor.MdcInitHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebMvcConfiguration {

	@Bean
	public WebMvcConfigurer initializerWebMvcConfigurer(MdcInitHandler mdcInitHandler, LoggingHandler loggingHandler) {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(mdcInitHandler);
				registry.addInterceptor(loggingHandler);
			}
		};
	}

}
