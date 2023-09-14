package springboot.EasyChair.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import springboot.EasyChair.security.CustomUserDetailsService;
import springboot.EasyChair.service.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final String secretKey;

    public SecurityConfig(JwtService jwtService, CustomUserDetailsService userDetailsService, @Value("${application.security.jwt.secret-key}") String secretKey) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
                .antMatchers("/api/register/**").permitAll() 
                .antMatchers("/api/index").permitAll() 
                .antMatchers("/api/login").permitAll() 
                .antMatchers("/api/users").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll()
                
        .anyRequest().authenticated()
        .and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().
        addFilterBefore(new JwtAuthorizationFilter(jwtService, userDetailsService, secretKey), 
        		UsernamePasswordAuthenticationFilter.class);
       
        
    }
}
