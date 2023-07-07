package com.company.WeGoDent.config;




import com.company.WeGoDent.security.AuthEntryPointJwt;
import com.company.WeGoDent.security.AuthTokenFilter;
import com.company.WeGoDent.security.HandlerAccessDeniedHandler;
import com.company.WeGoDent.security.HandlerAuthenticationEntryPoint;
import com.company.WeGoDent.security.services.LogoutService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Properties;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    private AuthTokenFilter authTokenFilter;


    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;


    @Autowired
    private LogoutService logoutService;



    @Bean // (1)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // (2)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }



    @Bean // (4)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


//        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));

        // @formatter:off


        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth ->  auth
                        .requestMatchers("/api/authenticate").permitAll()
                        .requestMatchers("/api/register").permitAll()
                        .requestMatchers("/api/logout").permitAll()
                        .requestMatchers("/api/allUsers").permitAll()
                        .requestMatchers("/api/admin/**").permitAll()
                        .requestMatchers("/api/appointment/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/api/blogposts/**").permitAll()
                        .requestMatchers("/api/patient/**").permitAll()
                        .requestMatchers("/api/blogcategory/**").permitAll()
                        .requestMatchers("/api/prescriptions/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/medicines/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/prescription_medicines/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/sick_leave_reports/**").hasAnyRole("DOCTOR", "ADMIN")
//                        -------------------------

                        .requestMatchers(HttpMethod.GET,"/api/doctor").permitAll()
                        .requestMatchers("/api/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/doctor/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/review/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/plan/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/inventories/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/inventory-items/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/inventory-categories/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/suppliers/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/treatments/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/treatment-phases/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/api/treatment-sessions/**").hasAnyRole("DOCTOR", "ADMIN")
                        .anyRequest().authenticated()

                ).logout((logout) -> logout
                        .logoutUrl("/api/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );


        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("farid.q@code.edu.az");
        mailSender.setPassword("uusnjmdxtesyxxgr");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("WeGoDent API Documentation")
                        .description("You can list and view APIs endpoints here")
                        .version("1.0"));
    }





}