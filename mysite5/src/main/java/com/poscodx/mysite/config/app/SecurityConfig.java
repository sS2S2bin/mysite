package com.poscodx.mysite.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web
            		.ignoring()
            		.requestMatchers(new AntPathRequestMatcher("/favicon.ico/**"))
            		.requestMatchers(new AntPathRequestMatcher("/assets/**"));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {	
    	// 3. security config 설정 1 - 작동유
    	http
    		// 3-1.FormLoginConfigurer 설정
    		.formLogin()
       		.loginPage("/user/login")
       		.loginProcessingUrl("/user/auth")
       		.usernameParameter("email")
       		.passwordParameter("password")
    		// 3-2. Acces control authorize 설정  
    		.and()
    		.authorizeHttpRequests(registry -> {
       			registry
       				/* ACL */
       				.requestMatchers(new RegexRequestMatcher("^/user/update$", null))
       				.hasAnyRole("ADMIN", "USER")
       	       		
       				.anyRequest()
       	       		.permitAll();
			});
       	
    	return http.build();
    }
}