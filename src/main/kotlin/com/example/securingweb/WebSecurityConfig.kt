package com.example.securingweb


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.*
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.*
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {

        val userDetails = User
            .withUsername("user")
            .password("{bcrypt}\$2a\$10\$pboYQQ24xlRU9sPIMXuZ.eMlgOOzAJ2xGRx0EmA/RVFrCuOzogdzi")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(userDetails)

    }
}