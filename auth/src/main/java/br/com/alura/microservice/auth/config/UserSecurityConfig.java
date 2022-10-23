package br.com.alura.microservice.auth.config;

import java.util.Collection;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().authenticated()
				.and()
					.csrf()
					.disable()
				.oauth2ResourceServer()
					.jwt()
					.jwtAuthenticationConverter(jwtAuthenticationConverter());

		return http.build();
	}

	/* 
	
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
		converter.setJwtGrantedAuthoritiesConverter(
				jwt -> {
					List<String> userRoleAuthorities = jwt.getClaimAsStringList("authorities");
					
					if(userRoleAuthorities == null) {
						return Collections.emptyList();
					}
					
					JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();
					var scopeAuthorities = scopesConverter.convert(jwt);

					
					scopeAuthorities.addAll(userRoleAuthorities.stream()
							.map(SimpleGrantedAuthority::new)
							.toList());
					return scopeAuthorities;
				}
		);
		return converter;
	}*/
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}
	
}