package com.example.studentoffice.ui.state.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.studentoffice.R
import com.example.studentoffice.model.User
import com.example.studentoffice.ui.state.main.MainActivity
import com.r0adkll.slidr.Slidr
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppTheme_Dark)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        User.getUser().clear()
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        setListeners()
    }

    private fun setLoginDataToUser() {
        User.getUser().email = editTextLogin.text.toString()
        User.getUser().password = editTextPassword.text.toString()
    }

    private fun goToMain() {
        val mIntent = Intent(this, MainActivity::class.java)
        startActivity(mIntent)
        finish()
    }

    private fun setListeners() {
        loginViewModel.response.observe(this, Observer {
            if (it.success) {
                goToMain()
            } else {
                Toast.makeText(this, "Не верный логин или пароль", Toast.LENGTH_LONG).show()
                buttonSignIn.visibility = View.VISIBLE
            }
        })
        buttonSignIn.setOnClickListener {
            setLoginDataToUser()
            buttonSignIn.visibility = View.GONE
            loginViewModel.login()
        }
    }
}