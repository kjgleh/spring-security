package com.example.springsecurity.dto

data class LoginRequest(
    val loginId: String,
    val password: String,
)