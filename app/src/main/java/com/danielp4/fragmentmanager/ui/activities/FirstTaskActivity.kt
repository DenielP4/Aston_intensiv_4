package com.danielp4.fragmentmanager.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.ui.fragments.task_1.FragmentA

class FirstTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_task)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.place_holder_1, FragmentA.newInstance())
                .commit()
        }

    }
}