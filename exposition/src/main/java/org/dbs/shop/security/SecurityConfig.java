package org.dbs.shop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// http.httpBasic() //
		// .and().authorizeRequests() //
		// .antMatchers(HttpMethod.GET, "/api/*").hasRole("USER") //
		// .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**",
		// "/webjars/**").permitAll()
		// .anyRequest().authenticated() //
		// .and().csrf().disable() //
		// .formLogin().disable();

		http.csrf().disable()//
		.formLogin()//
		.loginProcessingUrl("/login").successHandler(new AuthentificationLoginSuccessHandler()) //
		.failureHandler(new SimpleUrlAuthenticationFailureHandler())//
		.and() //
		.logout() //
		.logoutUrl("/logout").logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
		.invalidateHttpSession(true) //
		.and() //
		.authorizeRequests().antMatchers("/login").permitAll() //
		.antMatchers("/logout").permitAll()//
		.antMatchers("/api/**").authenticated().anyRequest().permitAll() //
		.and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
		});
	}

	private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
		@Override
		public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
				final Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
		@Override
		public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response,
				final Authentication authentication) throws IOException, ServletException {
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}
}
