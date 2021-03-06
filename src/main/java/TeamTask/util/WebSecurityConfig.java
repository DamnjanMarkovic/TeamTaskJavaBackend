package TeamTask.util;

import TeamTask.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/badToken").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/loginFacebookOrAppleUser").permitAll()
//				.antMatchers("/rest/users/getLoggedInUser/{id_user}/{jwt}").permitAll()
				.antMatchers("/rest/teams/ifExists/{id_team}").permitAll()
				.antMatchers("/rest/users/signUpUser").permitAll()
				.antMatchers("/rest/users/confirmRegistration").permitAll()
				.antMatchers("/rest/users/addNewUserInTeam").permitAll()
				.antMatchers("/rest/users/confirmRegistration{token}").permitAll()
				.antMatchers("/rest/users/checkIfUsernameExists/{username}").permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling().accessDeniedPage("/access-denied")
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
