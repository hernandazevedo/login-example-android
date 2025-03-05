package com.example.myapplication.data

import com.example.myapplication.presentation.LoginResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//mocking valid credentials
const val USER = "login"
const val PASSWORD = "password"
const val DUMMYSUCCESSTOKEN = "DUMMYSUCCESSTOKEN"

class LoginRepository {

    suspend fun callLogin(user: String, password: String): Flow<LoginRepositoryResult> = flow {

        if(user == USER && PASSWORD == password) {
            emit(LoginRepositoryResult(token = DUMMYSUCCESSTOKEN, valid = true))
        } else {
            emit(LoginRepositoryResult(valid = false, token = ""))
        }
    }
}