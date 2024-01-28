package com.example.springsecurity.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "members")
class Member(
    var loginId: String,
    var password: String,
    val name: String,
    val role: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0L
}