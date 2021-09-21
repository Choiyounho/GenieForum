package com.soten.genieforum.ui.select

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.soten.genieforum.R
import com.soten.genieforum.data.api.ApiKey.FORUM10
import com.soten.genieforum.data.api.ApiKey.FORUM2030
import com.soten.genieforum.data.api.ApiKey.FORUM4050
import com.soten.genieforum.databinding.ActivityContentSelectBinding
import com.soten.genieforum.ui.main.MainActivity

class ContentSelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContentSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_content_select)

        initViews()
    }

    private fun initViews() = with(binding) {
        val intent = Intent(this@ContentSelectActivity, MainActivity::class.java)
        age10.setOnClickListener {
            putExtra(intent, FORUM10)
        }
        age2030.setOnClickListener {
            putExtra(intent, FORUM2030)
        }
        age4050.setOnClickListener {
            putExtra(intent, FORUM4050)
        }

    }

    private fun putExtra(intent: Intent, age: String) {
        intent.putExtra(KEY, age)
        startActivity(intent)
    }

    companion object {
        const val KEY = "age"

        const val AGE10 = 10
        const val AGE2030 = 2030
        const val AGE4050 = 4050
    }

}