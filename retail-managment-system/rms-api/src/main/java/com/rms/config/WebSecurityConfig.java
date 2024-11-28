package com.rms.config;
import com.rms.security.CustomUserDetailsService;
import com.rms.security.RestAuthenticationEntryPoint;
import com.rms.security.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;




    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenAuthenticationFilter authenticationJwtTokenFilter() {
        return new TokenAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/auth/**").permitAll();
        http.authorizeRequests().antMatchers("/customer/**","/installment/**",
                "/investor/**","/employee/**","/profits/**"
                ,"/role/**","/store/**","/supplier/**",
                "/transaction/**","/user/**","/user-role/**").hasRole("ADMIN");
        //for swagger
        http.authorizeRequests().antMatchers("/v3/api-docs/*", "/v3/api-docs", "/swagger-ui.html", "/swagger-ui/*").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
