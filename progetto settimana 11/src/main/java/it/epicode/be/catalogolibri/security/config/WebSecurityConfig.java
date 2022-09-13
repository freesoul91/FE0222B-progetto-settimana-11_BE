package it.epicode.be.catalogolibri.security.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.epicode.be.catalogolibri.security.service.UserDetailsServiceImpl;
import it.epicode.be.catalogolibri.security.util.AuthEntryPointUnauthorizedJwt;
import it.epicode.be.catalogolibri.security.util.AuthTokenFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";*/
	
	/*@Autowired
	private AuthenticationManager authenticationManager;*/ // <----Controlla
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthEntryPointUnauthorizedJwt unauthorizedHandler;
	
	/*@Autowired
	private UserDetailsService userDetailsService;*/
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole(ADMIN)
			.antMatchers("/user").hasAnyRole(ADMIN, USER)
			.antMatchers("/all").permitAll()
			.and().formLogin();
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
		.userDetailsService(userDetailsService)
		.passwordEncoder(this.passwordEncoder());
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/public/**").permitAll()
		.anyRequest().authenticated();
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}*/
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception { // <----- Controlla
		return super.authenticationManager();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .headers().frameOptions().sameOrigin()
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll() // gli endpoint di autenticazione sono pubblicamente accessibili
        .antMatchers("/api/**").authenticated() // le API richiedono l'autenticazione
        .and() 
        .exceptionHandling()
        .authenticationEntryPoint((req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED : " + ex.getMessage()))
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // si abilita una gestione stateless
        .and()
        .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class); // si applica il filtro prima di processare la richiesta
    }


}
