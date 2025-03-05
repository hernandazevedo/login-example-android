package com.example.myapplication.data

import com.example.myapplication.domain.LoginResult

data class LoginRepositoryResult(val token: String, val valid: Boolean)

fun LoginRepositoryResult.toDomainObject() = LoginResult(
    token = token,
    valid = valid
)