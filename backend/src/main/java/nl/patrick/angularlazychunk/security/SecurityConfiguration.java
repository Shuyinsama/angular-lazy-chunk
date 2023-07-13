package nl.patrick.angularlazychunk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);
		firewall.setAllowSemicolon(true);
		return firewall;
	}

	@Bean
	public SecurityFilterChain angularSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement((sessionManagement ->
						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
				.authorizeHttpRequests(customizer -> customizer
						.requestMatchers(
								new AntPathRequestMatcher("/**"),
								new AntPathRequestMatcher("/js/**"))
						.permitAll())
				.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> {
			web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
			web.ignoring()
					// Always allow the 'CORS - preflight' call.
					.requestMatchers(new AntPathRequestMatcher(HttpMethod.OPTIONS.toString(), "/**"));

		};
	}
}
