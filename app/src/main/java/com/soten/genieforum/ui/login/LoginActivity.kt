package com.soten.genieforum.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.soten.genieforum.R
import com.soten.genieforum.databinding.ActivityLoginBinding
import com.soten.genieforum.extensions.ToastMessage.WELCOME_COMMENT
import com.soten.genieforum.extensions.toast
import com.soten.genieforum.ui.select.ContentSelectActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(1000L)
        }
        super.onCreate(savedInstanceState)
        observeData()
    }

    private fun initViews() {
        binding.loginButton.setOnClickListener {
            viewModel.signIn(getEmail(), getPassword())
        }

        binding.signUpText.setOnClickListener {
            viewModel.signUp(getEmail(), getPassword())
        }
    }

    private fun observeData() {
        viewModel.authLiveData.observe(this) { currentUser ->
            if (currentUser != null) {
                toast(WELCOME_COMMENT)
                startActivity(
                    Intent(this, ContentSelectActivity::class.java)
                )
            } else {
                setTheme(R.style.Theme_GenieForum)
                binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
                initViews()
            }
        }
    }

    private fun getEmail() = binding.inputEmailEditText.text.toString()

    private fun getPassword() = binding.inputPasswordEditText.text.toString()

}
