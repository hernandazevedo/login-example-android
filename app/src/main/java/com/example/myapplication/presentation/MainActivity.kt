package com.example.myapplication.presentation

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        val loginResult = binding.loginResult
        setContentView(binding.root)

        //TODO instantiate from viewmodel provider
        loginViewModel = LoginViewModel()


        loginViewModel.viewModelState.observe(this) {
            when(it) {
                is LoginResult.LoginSuccess -> startActivity(
                    LoginSuccessActivity.newIntent(
                        this,
                        it.token
                    )
                )

                LoginResult.Empty -> {

                }
                is LoginResult.LoginError -> {
                    loginResult.text = it.message
                }
            }
        }

        binding.buttonLogin.setOnClickListener {
            val login = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            //Send event to view to login with user/password
            loginViewModel.onEvent(LoginEvent.LoginClick(login = login, password = password))

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}