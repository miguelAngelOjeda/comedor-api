package py.com.coomecipar.service.web.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"py.com.coomecipar.service.web.**"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   

    @Bean
    public UserSession userSession() {
        return new UserSession();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection since tokens are immune to it
                .csrf().disable()
                // If the user is not authenticated, returns 401
                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // This is a stateless application, disable sessions
                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true).and()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Security policy
                .authorizeRequests()
                // Allow anonymous access to "/" path
                .antMatchers("/").permitAll()
                // Allow anonymous access to "/login" (only POST requests)
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/servicios/**").permitAll()
                .antMatchers("/updateauth/token").permitAll()
                // Any other request must be authenticated
                .anyRequest().authenticated().and()
                // Custom filter for logging in users at "/login"
                //.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // Custom filter for authenticating users using tokens
                //.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // Disable resource caching
                .headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(userSession());

        //auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }


}
