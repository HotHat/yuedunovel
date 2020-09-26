package com.lyhux.yuedunovel.ui.login

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.lyhux.yuedunovel.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModel<LoginViewModel>()

    private lateinit var submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel.response.observe(this, Observer {
            Log.e("LoginActivity", it.isSuccess.toString() + it.code)
            // if (it.isSuccess) {
            //     showToast(it.data!!.token)
            //     Log.e("LoginActivity", "success")
            // } else {
            //     showToast(it.message)
            //     Log.e("LoginActivity", "failure")
            // }
            it.doSuccess {
                showToast(it.token)
                Log.e("LoginActivity", "success")
            }.doError {
                showToast(it.message)
                Log.e("LoginActivity", "failure")
            }
        })

        submit = findViewById<Button>(R.id.submit)

        submit.setOnClickListener {
            loginViewModel.login()
        }

    }

    private fun showToast(info: String) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }
}

