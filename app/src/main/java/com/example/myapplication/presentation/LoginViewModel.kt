package com.example.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(val loginRepository: LoginRepository = LoginRepository()) : ViewModel() {
    //protecting state from outside changes
    private val _viewModelState = MutableLiveData<LoginResult>(LoginResult.Empty)
    val viewModelState: LiveData<LoginResult> get() = _viewModelState


    fun onEvent(event: LoginEvent) = viewModelScope.launch(Dispatchers.IO) {
        when(event) {
            is LoginEvent.LoginClick -> loginRepository.callLogin(event.login, event.password)
                .onEach {
                    if(it.valid)
                        _viewModelState.value = LoginResult.LoginSuccess(it.token)
                    else
                        _viewModelState.value = LoginResult.LoginError("Invalid credentials")
                }
                .launchIn(viewModelScope)
            else -> {}
        }
    }

}

sealed class LoginResult {
    object Empty : LoginResult()
    class LoginSuccess(val token: String) : LoginResult()
    class LoginError(val message: String) : LoginResult()
}

sealed class LoginEvent {
    object Empty : LoginEvent()
    class LoginClick(val login: String, val password: String) : LoginEvent()

}