package com.example.studentoffice.ui.state.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.model.AuthorizationResponse
import com.example.studentoffice.ui.state.login.LoginActivity
import com.example.studentoffice.ui.state.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var spalshViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spalshViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        spalshViewModel.response.observe(this, Observer {
            startActivityByResponse(it)
        })
    }

    private fun startActivityByResponse(loginResponse: AuthorizationResponse) {
        val mIntent : Intent = if (loginResponse.success) {
            Intent(this, MainActivity::class.java)
        } else if (loginResponse.isFail != null && loginResponse.isFail) {
            Toast.makeText(this, "Man, we have problem with connection!", Toast.LENGTH_SHORT).show()
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        startActivity(mIntent)
        //flag
        finish()
    }
}