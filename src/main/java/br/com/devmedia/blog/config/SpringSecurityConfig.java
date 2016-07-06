package br.com.devmedia.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/",
                         "/{permalink}",
                         "/search/**",
                         "/autor/{id}/page/{page}",
                         "/categoria/{link}/page/{page}",
                         "/page/{page}").permitAll()
                .antMatchers("/avatar/load/{id}").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/auth/login")
                    .failureUrl("/auth/login?error=true")
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                .permitAll()
            .and()
                .logout()
                    .logoutSuccessUrl("/auth/login?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
            .and()
                .exceptionHandling().accessDeniedPage("/auth/denied");
    }
}