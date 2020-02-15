package fernando.murta.wallet.config;

import fernando.murta.wallet.core.SecurityRoles;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Fernando Murta
 * @version 0.0.1 - BETA
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	/**
	 * Method to put some rule about the Http Security
	 *
	 * @param http HttpSecurity
	 * @throws Exception Exception if anything goes wrong
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/**").hasRole(SecurityRoles.USER.getRole())
				.antMatchers(HttpMethod.PUT, "/**").hasRole(SecurityRoles.ADMIN.getRole())
				.antMatchers(HttpMethod.PATCH, "/**").hasRole(SecurityRoles.ADMIN.getRole())
				.antMatchers(HttpMethod.DELETE, "/**").hasRole(SecurityRoles.ADMIN.getRole())
				.and()
				.csrf().disable()
				.formLogin().disable();
	}

	/**
	 * Method to create 2 User´s in memory to test Basic Auth in the API
	 *
	 * @param auth Authentication
	 * @throws Exception Exception if anything goes wrong
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}user")
				.roles(SecurityRoles.USER.getRole())
				.and()
				.withUser("admin").password("{noop}admin")
				.roles(SecurityRoles.USER.getRole(), SecurityRoles.ADMIN.getRole());

	}
}
