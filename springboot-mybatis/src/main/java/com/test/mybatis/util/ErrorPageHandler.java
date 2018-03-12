package com.test.mybatis.util;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageHandler {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
	return new EmbeddedServletContainerCustomizer() {

	    @Override
	    public void customize(ConfigurableEmbeddedServletContainer container) {
		ErrorPage errorPage_401 = new ErrorPage(HttpStatus.UNAUTHORIZED,"/401.html");
		ErrorPage errorPage_404 = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
		ErrorPage errorPage_405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405.html");
		ErrorPage errorPage_500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
		container.addErrorPages(errorPage_401, errorPage_404,errorPage_405,errorPage_500);
	    }
	};
    }

}
