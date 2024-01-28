package com.example.springsecurity.dto

data class SignupRequest(
    val loginId: String,
    val name: String,
    val password: String,
    val role: String,
)