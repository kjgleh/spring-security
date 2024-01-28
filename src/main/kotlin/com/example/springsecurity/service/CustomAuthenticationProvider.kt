package com.example.springsecurity.service

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationProvider(
    val userDetailsService: CustomUserDetailsService,
    val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()

        val user = userDetailsService.loadUserByUsername(username)

        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("인증 실패")
        }

        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}