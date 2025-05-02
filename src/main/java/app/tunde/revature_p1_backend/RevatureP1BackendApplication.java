package app.tunde.revature_p1_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import app.tunde.revature_p1_backend.filters.AuthFilter;



@SpringBootApplication
public class RevatureP1BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevatureP1BackendApplication.class, args);
	}

	// -- Implement a route Filter here --
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns(
			"/api/products/*", 
			"/api/users/me"
		);
		return registrationBean;
	}

}
