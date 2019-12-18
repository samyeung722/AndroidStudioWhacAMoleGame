package com.example.manyeung_cs376finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score: Int = 0
    var imageArray = ArrayList<ImageView>()
    var imageArray2 = ArrayList<ImageView>()
    var handler: Handler = Handler()
    var runnable: Runnable = Runnable { }
    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0

        imageArray = arrayListOf(
            imageView,
            imageView2,
            imageView3,
            imageView4,
            imageView5,
            imageView6,
            imageView7,
            imageView8,
            imageView9
        )

        imageArray2 = arrayListOf(
            imageView00,
            imageView22,
            imageView33,
            imageView44,
            imageView55,
            imageView66,
            imageView77,
            imageView88,
            imageView99
        )

        makeNewImageVisible()
        //delayHandle()

        object : CountDownTimer(15000, 1000) {
            override fun onFinish() {
                timerText.text = "Time END!"
                handler.removeCallbacks(runnable)
                handler.removeCallbacksAndMessages(makeNewImageVisible())

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                for (image in imageArray2) {
                    image.visibility = View.INVISIBLE
                }

                Handler().postDelayed({
                    for (image in imageArray) {
                        image.visibility = View.INVISIBLE
                    }

                    for (image in imageArray2) {
                        image.visibility = View.INVISIBLE
                    }
                }, 200)
            }

            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Time: " + millisUntilFinished / 1000
            }

        }.start()
    }

    private fun delayHandle() {

        /*
        runnable = object : Runnable {
            override fun run() {
                imageArray2[index].visibility = View.VISIBLE
                handler.postDelayed(runnable, 1000)
            }
        }
        */

        imageArray[index].visibility = View.INVISIBLE

        imageArray2[index].visibility = View.VISIBLE


        Handler().postDelayed({
            makeNewImageVisible()
        }, 200)
    }

    fun increaseScore(view: View) {
        score += 64

        scoreText.text = "Score: " + score

        delayHandle()
    }

    private fun makeNewImageVisible() {
        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }

        for (image in imageArray2) {
            image.visibility = View.INVISIBLE
        }

        val random = Random()
        index = random.nextInt(8 - 0)
        imageArray[index].visibility = View.VISIBLE
    }


    fun backToMenu(view: View) {
        startActivity(Intent(applicationContext, start::class.java))
    }
}
