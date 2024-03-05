package com.danielp4.fragmentmanager.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielp4.fragmentmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            bFirstTask.setOnClickListener {
                val intent = Intent(this@MainActivity, FirstTaskActivity::class.java)
                startActivity(intent)
            }
            bSecondTask.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondTaskActivity::class.java)
                startActivity(intent)
            }

        }

    }
}