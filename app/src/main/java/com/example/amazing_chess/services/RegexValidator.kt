package com.example.amazing_chess.services

class RegexValidator {
    private val emailRegex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$".toRegex()

    fun validateEmail(string: String): Boolean = string.matches(emailRegex)
}