package com.example.springsecurity.controller

import com.example.springsecurity.dto.SignupRequest
import com.example.springsecurity.service.MemberService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(
    private val memberService: MemberService,
) {

    @GetMapping("/members/signup")
    fun signupForm(): String {
        return "/member/signup"
    }

    @PostMapping("/members/signup")
    fun signup(@ModelAttribute signupRequest: SignupRequest): String {
        memberService.signup(signupRequest)

        return "redirect:/"
    }

    @GetMapping("/members/login")
    fun login(): String {
        return "/member/login"
    }

    @GetMapping("/members/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val auth = SecurityContextHolder.getContext().authentication

        auth?.let {
            SecurityContextLogoutHandler().logout(request, response, it)
        }

        return "redirect:/"
    }
}