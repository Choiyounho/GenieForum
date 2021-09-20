package com.soten.genieforum.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.soten.genieforum.R
import com.soten.genieforum.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel.authLiveData.observe(this) {
            if (it != null) {
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }
        }

        binding.loginButton.setOnClickListener {
            viewModel.signUp(getEmail(), getPassword())
        }
    }

    private fun getEmail() = binding.inputEmailEditText.text.toString()

    private fun getPassword() = binding.inputPasswordEditText.text.toString()
}