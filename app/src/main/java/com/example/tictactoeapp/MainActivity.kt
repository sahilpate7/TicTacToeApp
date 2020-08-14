package com.example.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var player1Score = 0
    var player2Score = 0
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCells = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        freset.setOnClickListener{
            finalReset()
        }
    }

    fun buClick(view: View) {

        val buSelected = view as Button

        var cellID = 0
        when (buSelected.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }

        //Log.d("buClick",buSelected.id.toString())
        //Log.d("buClick: cellID",cellID.toString())
        playGame(cellID, buSelected)
    }

    var activePlayer = 1


    fun playGame(cellID: Int, buSelected: Button) {

        if (activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            activePlayer = 2

            //autoPlay()
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.darkGreen)
            player2.add(cellID)
            activePlayer = 1

        }
        val winner = checkWinner()
        if(winner>0)
            processWinner(winner)
        buSelected.isEnabled = false
    }

     fun autoPlay() {
         emptyCells.clear()
         for (cellID in 1..9) {
            if (!player1.contains(cellID) && !player2.contains((cellID))) {
                emptyCells.add(cellID)
            }
        }



         if(emptyCells.isNotEmpty()){
             val randIndex=Random.nextInt(emptyCells.size)
             val cellID=emptyCells[randIndex]

             var buSelected:Button?
             buSelected=when(cellID){
                 1-> bu1
                 2-> bu2
                 3-> bu3
                 4-> bu4
                 5-> bu5
                 6-> bu6
                 7-> bu7
                 8-> bu8
                 9-> bu9
                 else->{ bu1}
             }

             buSelected.text = "O"
             buSelected.setBackgroundResource(R.color.darkGreen)
             player2.add(cellID)
             activePlayer = 1
             val winner = checkWinner()
             if(winner>0)
                 processWinner(winner)
         }

    }

    fun checkWinner():Int {
        var winner = -1

        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //column 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //column 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //column 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //cross 1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //cross 2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        return winner
    }

    fun processWinner(winner :Int){

        if (winner == 1) {
            activePlayer=1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            score(winner)
            Handler().postDelayed(Runnable {
                reset()
            },2000)
        } else if (winner == 2) {
            activePlayer=2
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
            score(winner)
            Handler().postDelayed(Runnable {
                reset()
            },2000)
        }
    }
    fun score(winner :Int){
        if (winner==1){
            player1Score++
            score1.text = player1Score.toString()
        } else if(winner==2){
            player2Score++
            score2.text = player2Score.toString()
        }
    }
    fun reset(){
            bu1.text=""
            bu1.isEnabled = true
            bu1.setBackgroundResource(R.color.white)
            bu2.text=""
            bu2.isEnabled=true
            bu2.setBackgroundResource(R.color.white)
            bu3.text=""
            bu3.isEnabled=true
            bu3.setBackgroundResource(R.color.white)
            bu4.text=""
            bu4.isEnabled=true
            bu4.setBackgroundResource(R.color.white)
            bu5.text=""
            bu5.isEnabled=true
            bu5.setBackgroundResource(R.color.white)
            bu6.text=""
            bu6.isEnabled=true
            bu6.setBackgroundResource(R.color.white)
            bu7.text=""
            bu7.isEnabled=true
            bu7.setBackgroundResource(R.color.white)
            bu8.text=""
            bu8.isEnabled=true
            bu8.setBackgroundResource(R.color.white)
            bu9.text=""
            bu9.isEnabled=true
            bu9.setBackgroundResource(R.color.white)
            player1.clear()
            player2.clear()
            emptyCells.clear()
    }
    fun finalReset(){
        player1Score=0
        score1.text="0"
        player2Score=0
        score2.text="0"
        reset()
    }
}