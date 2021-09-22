package com.soten.genieforum.ui.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.soten.genieforum.R
import com.soten.genieforum.adapter.OnBoardingAdapter
import com.soten.genieforum.databinding.ActivityOnBoardingBinding
import com.soten.genieforum.ui.select.ContentSelectActivity
import com.soten.genieforum.util.GlobalData

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    private val adapter by lazy { OnBoardingAdapter() }

    private val callback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {

            val commentList = listOf(
                "고민이 있으신가요?",
                "해결사가 있습니다",
                "바로 저요!"
            )

            binding.pagerDescription.run {
                text = commentList[position]
            }
            binding.pagerIndicator01.isActivated = position == 0
            binding.pagerIndicator02.isActivated = position == 1
            binding.pagerIndicator03.isActivated = position == 2
            binding.startButton.isVisible = position == 2
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)

        binding.viewPager2.adapter = adapter
        binding.nextButton.setOnClickListener {
            binding.viewPager2.currentItem += 1
        }
        binding.startButton.setOnClickListener {
            GlobalData.showOnBoarding = true
            startActivity(
                Intent(this, ContentSelectActivity::class.java)
            )
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.viewPager2.registerOnPageChangeCallback(callback)
    }

    override fun onPause() {
        super.onPause()
        binding.viewPager2.unregisterOnPageChangeCallback(callback)
    }

    override fun onBackPressed() {
        if (binding.viewPager2.currentItem > 0) {
            binding.viewPager2.currentItem -= 1
        } else {
            super.onBackPressed()
        }
    }

}