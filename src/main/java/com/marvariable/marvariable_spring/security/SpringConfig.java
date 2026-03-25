package com.marvariable.marvariable_spring.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;

@Configuration
public class SpringConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    public SpringConfig(CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(customAuthenticationManager,
                jwtSecret);
        authenticationFilter.setFilterProcessesUrl("/login");

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
    .requestMatchers("/login").permitAll()
    .requestMatchers("/api/user/register").permitAll()
    .requestMatchers(HttpMethod.GET, "/api/publications").permitAll()
    .requestMatchers(HttpMethod.GET, "/api/publications/**").permitAll()
    .requestMatchers(HttpMethod.GET, "/api/visual-arts").permitAll()
    .requestMatchers(HttpMethod.GET, "/api/visual-arts/**").permitAll()
    .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
    .requestMatchers(HttpMethod.GET, "/uploads/**").permitAll()
    .requestMatchers(HttpMethod.GET, "/files/**").permitAll()
    .requestMatchers(HttpMethod.DELETE, "/api/publications/**").permitAll()
    .requestMatchers(HttpMethod.DELETE, "/api/visual-arts/**").permitAll()
    .requestMatchers(HttpMethod.PUT, "/api/publications/**").permitAll()
    .requestMatchers(HttpMethod.PUT, "/api/visual-arts/**").permitAll()
    .anyRequest().authenticated()
)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(jwtSecret), JWTAuthenticationFilter.class)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:5174"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}