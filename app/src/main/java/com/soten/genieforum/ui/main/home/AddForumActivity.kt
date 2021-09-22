package com.soten.genieforum.ui.main.home

import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.soten.genieforum.R
import com.soten.genieforum.data.api.ApiKey.FORUM10
import com.soten.genieforum.data.api.ApiKey.FORUM2030
import com.soten.genieforum.data.api.ApiKey.FORUM4050
import com.soten.genieforum.databinding.ActivityAddForumBinding
import com.soten.genieforum.di.ForumModule.provideFirebaseAuth
import com.soten.genieforum.domain.model.Forum
import com.soten.genieforum.domain.model.Topic
import com.soten.genieforum.util.TimeUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddForumBinding

    private val viewModel by viewModels<AddForumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_forum)

        initViews()
    }

    private fun initViews() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        ArrayAdapter.createFromResource(
            this,
            R.array.category,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            binding.categorySpinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.selectAge,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            binding.ageSpinner.adapter = adapter
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addButton.setOnClickListener {
            viewModel.addForum(
                getAgeCategory(),
                Forum(
                    title = binding.detailTitle.text.toString(),
                    description = binding.detailDescription.text.toString(),
                    createdAt = TimeUtil.createdTimeForId(),
                    topic = getCategory(),
                    userName = provideFirebaseAuth().currentUser?.uid?.substring(0, 5)
                )
            )
            finish()
        }
    }

    private fun getCategory() =
        when (binding.categorySpinner.selectedItem.toString()) {
            "자유" -> Topic.FREE.name
            "연애" -> Topic.LOVE.name
            "여행" -> Topic.TRIP.name
            else -> Topic.EXERCISE.name
        }

    private fun getAgeCategory() =
        when (binding.ageSpinner.selectedItem.toString()) {
            "10" -> FORUM10
            "2030" -> FORUM2030
            else -> FORUM4050
        }
/*
* const val FORUM10 = "Forums"
    const val FORUM2030 = "Forums2030"
    const val FORUM4050 = "Forums4050"*/
}