package com.gdsc.wap_game

import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.animation.addListener
import androidx.fragment.app.Fragment
import com.gdsc.wap_game.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private var drawable: AnimationDrawable? = null
    private var curPosX: Float = 0f
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {}
    private lateinit var gameBinding : FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameBinding = FragmentGameBinding.inflate(inflater,container,false)
        return gameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPet(gameBinding.enemyImageView)
    }
    private fun initPet(pet: ImageView) {
        handler.removeCallbacks(runnable)
        runnable = object : Runnable {
            override fun run() {
                val delay = (0..10000).random().toLong()
                movePet(pet)
                handler.postDelayed(runnable, delay)
                Log.d("MainActivity", "$delay")
            }
        }
        handler.post(runnable)
    }

    private fun movePet(pet: ImageView) {
        val nextPosX = ((-30..30).random() * 10).toFloat()

        ObjectAnimator.ofFloat(pet, "translationX", nextPosX).apply {
            duration = 2000
            addListener(onStart = { changePetAnimation("walk", nextPosX) },
                onEnd = { changePetAnimation("stop") })
            start()
        }
    }

    private fun changePetAnimation(state: String, nextPosX: Float = 0f) {
        drawable?.stop()
        when (state) {
            "walk" -> {
                gameBinding.enemyImageView.setBackgroundResource(R.drawable.pet_walk_animation)
                gameBinding.enemyImageView.scaleX = if (curPosX - nextPosX > 0) 1f else -1f
                curPosX = nextPosX
            }
            "stop" -> gameBinding.enemyImageView.setBackgroundResource(R.drawable.pet_idle_animation)
        }
        drawable = gameBinding.enemyImageView.background as AnimationDrawable
        drawable?.start()
    }
}