package com.example.springsecurity.repository

import com.example.springsecurity.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(username: String): Member?
}