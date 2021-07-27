package com.example.apiproductostiendaconspringsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.apiproductostiendaconspringsecurity.security.UserPermission.PRODUCTO_WRITE;
import static com.example.apiproductostiendaconspringsecurity.security.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/index.html").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/productos/**").hasAuthority(PRODUCTO_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/**").hasAuthority(PRODUCTO_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE,"/api/productos/**").hasAnyRole(ADMIN.getRole())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(5); //cuantas vueltas da el algoritmo??-5

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails usuario1 = User.builder()
                .username("pablo")
                .password(passwordEncoder().encode("unafacil"))
                .roles("ADMIN")
                .build();

        UserDetails usuario2= User.builder()
                .username("geraldine")
                .password(passwordEncoder().encode("unafacil"))
                .roles("ADMIN")
                .build();
        UserDetails usuario3= User.builder()
                .username("jazmin")
                .password(passwordEncoder().encode("unafacil"))
                .roles("CLIENTE")
                .build();
        UserDetails usuario4= User.builder()
                .username("natalia")
                .password(passwordEncoder().encode("unafacil"))
                .roles("CLIENTE")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3, usuario4);
    }
}
