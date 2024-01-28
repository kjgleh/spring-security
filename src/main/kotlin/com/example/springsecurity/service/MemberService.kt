package com.example.springsecurity.service

import com.example.springsecurity.domain.Member
import com.example.springsecurity.dto.SignupRequest
import com.example.springsecurity.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun signup(request: SignupRequest) {
        val member = Member(
            loginId = request.loginId,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            role = request.role
        )

        memberRepository.save(member)
    }
}