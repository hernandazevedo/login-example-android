package com.example.myapplication.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

const val EXTRA_TOKEN = "EXTRA_TOKEN"
class LoginSuccessActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context, successToken: String): Intent {
            return Intent(context, LoginSuccessActivity::class.java).apply {
                putExtra(EXTRA_TOKEN, successToken)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_success)
    }
}