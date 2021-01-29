/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.game

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Loads [MainFragment].
 */
class MainActivity : Activity() {

    @SuppressLint("SetTextI18n", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images: MutableList<Int> = mutableListOf(
            R.drawable.england, R.drawable.france,
            R.drawable.germany, R.drawable.holland, R.drawable.lithuania, R.drawable.portugal,
            R.drawable.spain, R.drawable.turkey, R.drawable.england, R.drawable.france,
            R.drawable.germany, R.drawable.holland, R.drawable.lithuania, R.drawable.portugal,
            R.drawable.spain, R.drawable.turkey
        ) // png formatında oluşturduğumuz iconları integer listte tuttuk
        val buttons :Array<Button> = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8,button9, button10, button11, button12,button13,button14,button15,button16)
        val front = R.drawable.questionmark
        var FirstClicked = 0
        var turnOver = false
        var lastClicked = -1
        images.shuffle()
        val score = findViewById(R.id.textView2) as TextView
        score.text = View.VISIBLE.toString()   //elde edilen score string değere dönüştürdük ve ekrana yazdırdık
        val scoreText = findViewById(R.id.txtScore) as TextView // score bilgisini text field da tutuyoruz
        scoreText.text = "Score :"
        val refreshBtn = findViewById<Button>(R.id.btnRetry)
        refreshBtn.setOnClickListener {
            val refreshActivity = intent
            finish()
            startActivity(refreshActivity)
        }
        refreshBtn.visibility = View.INVISIBLE
        var count = 0
        var scoreInt = 0
        for (i in 0..15) { // oluşturduğumuz buton sayısı kadar loop dönecek
            buttons[i].setBackgroundResource(front)
            buttons[i].text = "front"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "front" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (FirstClicked == 0) {
                        lastClicked = i
                    }
                    FirstClicked++
                } else if (buttons[i].text !in "front") {
                    buttons[i].setBackgroundResource(front)
                    buttons[i].text = "front"
                    FirstClicked--
                }

                if (FirstClicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        count++
                        scoreInt++
                        score.text = scoreInt.toString() // text field a yazdırmak için integer sayıyı string e dönüştürdük
                        buttons[i].visibility = View.INVISIBLE   //
                        buttons[lastClicked].visibility =
                            View.INVISIBLE // eşleşme oldugunda resimleri ekrandan kaldırmak için kullandım
                        turnOver = false
                        FirstClicked = 0

                        if (count == 8) {
                            refreshBtn.visibility =
                                View.VISIBLE // resimler eşleştikten sonra yenileme butonunu aktif ettik
                        }
                    }
                } else if (FirstClicked == 0) {
                    turnOver = false
                }
                else if(buttons[i].text != buttons[lastClicked].text ){ // buttonların indexleri eşit değil ise en son tıkladığımız butona tıkladığımızda acılan resimleri direk kapatıyor
                    buttons[i].setBackgroundResource(front)
                    buttons[i].text = "front"
                    buttons[lastClicked].setBackgroundResource(front)
                    buttons[lastClicked].text = "front"
                    turnOver = false
                    FirstClicked = 0
                }
                
            }
        }
    }
}