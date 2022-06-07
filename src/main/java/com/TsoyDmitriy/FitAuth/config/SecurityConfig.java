package com.TsoyDmitriy.FitAuth.config;

import com.TsoyDmitriy.FitAuth.config.handler.MyAuthEntryPoint;
import com.TsoyDmitriy.FitAuth.config.handler.MyAuthFailureHandler;
import com.TsoyDmitriy.FitAuth.config.handler.MyAuthSuccessHandler;
import com.TsoyDmitriy.FitAuth.config.handler.MyLogoutSuccessHandler;
import com.TsoyDmitriy.FitAuth.service.UserService;
import com.TsoyDmitriy.FitAuth.util.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.TsoyDmitriy.FitAuth.config.jwt.JwtProvider.TOKEN;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final MyAuthEntryPoint entryPoint;
    private final MyAuthFailureHandler failureHandler;
    private final MyAuthSuccessHandler successHandler;
    private final MyLogoutSuccessHandler logoutSuccessHandler;
    private final Encoder encoderProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoderProvider);
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                .invalidateHttpSession(true).deleteCookies(TOKEN)
                .permitAll()
                .and()
                .formLogin()
                .successHandler(successHandler).failureHandler(failureHandler)
                .loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
