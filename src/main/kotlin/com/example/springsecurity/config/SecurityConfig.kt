package com.example.springsecurity.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/", "/signup").permitAll()
                    .requestMatchers("/mypage").hasRole("MEMBER")
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .formLogin {
                it
                    .loginPage("/members/login")
                    .permitAll()
            }
            .logout {
                it
                    .logoutUrl("/members/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
            }
        return http.build()
    }

//    @Bean
//    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
//        val users = listOf(
//            User.builder().username("user").password(passwordEncoder.encode("1111")).roles("USER").build(),
//            User.builder().username("admin").password(passwordEncoder.encode("1111")).roles("ADMIN").build(),
//        )
//
//        return InMemoryUserDetailsManager(users)
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}