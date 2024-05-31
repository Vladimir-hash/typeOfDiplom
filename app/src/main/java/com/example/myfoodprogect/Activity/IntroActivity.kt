package com.example.myfoodprogect.Activity

import android.content.Intent
import android.os.Bundle
import com.example.myfoodprogect.databinding.ActivityIntroBinding

class IntroActivity : BasicActivity() {
    private lateinit var binding:ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }
    }
}