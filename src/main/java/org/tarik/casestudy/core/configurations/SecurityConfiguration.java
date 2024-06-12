package org.tarik.casestudy.core.configurations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tarik.casestudy.core.filters.JwtAuthenticationFilter;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.abstracts.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    private final RoleService roleService;

    private static final String[] WHITE_LIST_URLS = {
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/v1/auth/**",
            "/api/v1/register/**",
    };
    private static final String[] MANAGER_CREATE = {
            "/api/**"
    };
    private static final String[] MANAGER_UPDATE = {
            "/api/**"
    };
    private static final String[] MANAGER_DELETE = {
            "/api/**"
    };
    private static final String[] MANAGER_GET = {
            "/api/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        Role managerRole = roleService.getByName("MANAGER");

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x.requestMatchers(WHITE_LIST_URLS).permitAll()
                                .requestMatchers(HttpMethod.GET, MANAGER_GET).hasAuthority(managerRole.getName())
                                .requestMatchers(HttpMethod.POST, MANAGER_CREATE).hasAuthority(managerRole.getName())
                                .requestMatchers(HttpMethod.PUT, MANAGER_UPDATE).hasAuthority(managerRole.getName())
                                .requestMatchers(HttpMethod.DELETE, MANAGER_DELETE).hasAuthority(managerRole.getName())
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
