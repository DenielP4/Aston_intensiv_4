package com.danielp4.fragmentmanager.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.UserViewModel
import com.danielp4.fragmentmanager.ui.fragments.task_2.ListUserFragment

class SecondTaskActivity : AppCompatActivity() {

    val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_task)
        if (viewModel.listUser.value==null) {
            viewModel.init()
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.place_holder_2, ListUserFragment.newInstance())
                .commit()
        }
    }
}