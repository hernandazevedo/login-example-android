package com.example.myapplication.presentation

import com.example.myapplication.data.LoginRepository
import com.example.myapplication.data.toDomainObject
import kotlinx.coroutines.flow.map

class LoginUseCase(private val loginRepository: LoginRepository = LoginRepository()) {
    suspend operator fun invoke(login: String, password: String) =
        loginRepository.callLogin(login, password).map { it.toDomainObject() }
}