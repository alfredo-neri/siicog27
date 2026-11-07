package com.gem.sistema.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.gem.sistema.web.security.filters.AjaxTimeoutRedirectFilter;

/**
 * Equivalente de WEB-INF/spring/applicationContext-security.xml (Spring Security 4.1 -> 7).
 *
 * Se conserva el comportamiento del XML: login JSF en /login.xhtml que envia a
 * /j_spring_security_check, logout en /logout, CSRF desactivado, maximo 8 sesiones concurrentes y
 * el filtro de expiracion para peticiones AJAX justo despues de ExceptionTranslationFilter.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/** Las contrasenas existentes son BCrypt sin prefijo {bcrypt}: no se usa DelegatingPasswordEncoder. */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			@Qualifier("authenticationProvider") AuthenticationProvider authenticationProvider) {
		return new ProviderManager(authenticationProvider);
	}

	/** {@code <sec:http pattern="..." security="none"/>} */
	@Bean
	public WebSecurityCustomizer ignoredPaths() {
		return web -> web.ignoring().requestMatchers("/resources/**", "/login.xhtml");
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter() {
		AjaxTimeoutRedirectFilter filter = new AjaxTimeoutRedirectFilter();
		filter.setCustomSessionExpiredErrorCode(901);
		return filter;
	}

	/** El filtro solo debe correr dentro de la cadena de seguridad, no como filtro global de Tomcat. */
	@Bean
	public FilterRegistrationBean<AjaxTimeoutRedirectFilter> ajaxTimeoutRedirectFilterRegistration(
			AjaxTimeoutRedirectFilter filter) {
		FilterRegistrationBean<AjaxTimeoutRedirectFilter> registration = new FilterRegistrationBean<>(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager,
			SessionRegistry sessionRegistry, AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter) throws Exception {
		http.authenticationManager(authenticationManager)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/monitoring/**", "/jakarta.faces.resource/**", "/login.xhtml", "/apariencia")
						.permitAll()
						// panel Apariencia y demas opciones de administracion
						.requestMatchers("/views/administracion/**").hasRole("ADMIN")
						.requestMatchers("/views/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/tmp/**", "/home.xhtml").authenticated()
						// el XML no tenia regla final: lo no listado quedaba accesible
						.anyRequest().permitAll())
				.formLogin(form -> form
						.loginPage("/login.xhtml")
						.loginProcessingUrl("/j_spring_security_check")
						.usernameParameter("username")
						.passwordParameter("password")
						.defaultSuccessUrl("/home.xhtml")
						.failureUrl("/login.xhtml")
						.permitAll())
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login.xhtml")
						.invalidateHttpSession(true))
				.sessionManagement(session -> session
						.sessionFixation(fixation -> fixation.newSession())
						.invalidSessionUrl("/login.xhtml")
						.sessionAuthenticationErrorUrl("/login.xhtml")
						.maximumSessions(8)
						.maxSessionsPreventsLogin(true)
						.expiredUrl("/login.xhtml")
						.sessionRegistry(sessionRegistry))
				.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
				.addFilterAfter(ajaxTimeoutRedirectFilter, ExceptionTranslationFilter.class);
		return http.build();
	}

	/** Antes era un listener en web.xml; necesario para el control de sesiones concurrentes. */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
