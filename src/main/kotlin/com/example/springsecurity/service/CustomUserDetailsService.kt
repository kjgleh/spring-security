package com.example.springsecurity.service

import com.example.springsecurity.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByLoginId(username) ?: throw UsernameNotFoundException("사용자 조회 실패: $username")

        return User(username, member.password, listOf(SimpleGrantedAuthority(member.role)))
    }
}