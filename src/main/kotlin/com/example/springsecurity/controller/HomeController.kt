package com.example.springsecurity.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping("/mypage")
    fun mypage(): String {
        return "mypage"
    }

    @GetMapping("/admin")
    fun admin(): String {
        return "admin"
    }
}