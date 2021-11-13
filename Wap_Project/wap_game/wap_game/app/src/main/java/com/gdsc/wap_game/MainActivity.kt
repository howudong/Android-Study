package com.gdsc.wap_game

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.animation.addListener
import com.gdsc.wap_game.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadFragmentButton.setOnClickListener{
            setFrag()
        }
    }

    private fun setFrag() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout,GameFragment()).commit()

    }
}

