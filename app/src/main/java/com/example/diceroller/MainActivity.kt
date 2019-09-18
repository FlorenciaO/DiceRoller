package com.example.diceroller

import android.support.v7.app.AppCompatActivity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var rollButton : Button
    private lateinit var numberTextView : TextView
    private lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.roll_button)
        numberTextView = findViewById(R.id.number_textview)
        diceImage = findViewById(R.id.dice_imageview)

        rollButton.setOnClickListener {
            diceRoller(1000)
        }

    }

    private fun diceRoller(duration : Long) {
        rollButton.text = getString(R.string.rolling_message)
        rotate(duration)
        val randomInt = (1..6).random()
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val handler = Handler()
        handler.postDelayed({
            numberTextView.text = randomInt.toString()
            diceImage.setImageResource(drawableResource)
            rollButton.text = getString(R.string.roll_message)
        }, duration)
    }

    private fun rotate(duration : Long) {
        val rotate = ObjectAnimator.ofFloat(diceImage, View.ROTATION, -360f, 0f)
        rotate.duration = duration
        rotate.start()

    }
}
