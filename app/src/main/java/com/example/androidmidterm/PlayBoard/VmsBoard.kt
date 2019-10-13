package com.example.androidmidterm.PlayBoard

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.MY_COLOR
import com.example.androidmidterm.Services.indexOf2D
import com.example.androidmidterm.Services.isSubBoard
import com.example.androidmidterm.Services.toResource
import kotlinx.android.synthetic.main.activity_vms_board.*

class VmsBoard: Board() {

    private lateinit var boardLocation: Array<Array<ImageView>>
    private val MAIN = R.drawable.bg_vms_main_board
    private val SUB = R.drawable.bg_vms_sub_board
    val Board = super.MainBoard
    var isRed = false
    var redList = listOf<Pair<Int, Int>>()
    var pos = Pair(-1, -1)

    fun btnClicked(view: View) {
        val pair = boardLocation.indexOf2D(view)
        if (!isRed) {
            val possibleMove = Board[pair.first][pair.second].possibleMove(pair, Board)
            for (i in possibleMove) {
                boardLocation[i.first][i.second].setBackgroundResource(R.drawable.bg_target)
                boardLocation[i.first][i.second].isClickable = true
            }

            if (possibleMove.isNotEmpty()) {
                isRed = true
                redList = possibleMove
            }
            pos = pair
        } else {
            if (redList.contains(pair)) {
                super.move(pos, pair)
                updateBoardUI()
            }
            for (i in redList) {
                boardLocation[i.first][i.second].setBackgroundResource(if(isSubBoard(i)) SUB else MAIN)
            }
            isRed = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vms_board)

        boardLocation = arrayOf(
            arrayOf(a1, a2, a3, a4, a5, a6, a7, a8),
            arrayOf(b1, b2, b3, b4, b5, b6, b7, b8),
            arrayOf(c1, c2, c3, c4, c5, c6, c7, c8),
            arrayOf(d1, d2, d3, d4, d5, d6, d7, d8),
            arrayOf(e1, e2, e3, e4, e5, e6, e7, e8),
            arrayOf(f1, f2, f3, f4, f5, f6, f7, f8),
            arrayOf(g1, g2, g3, g4, g5, g6, g7, g8),
            arrayOf(h1, h2, h3, h4, h5, h6, h7, h8))
        updateBoardUI()
    }

    private fun updateBoardUI() {
        for(x in 0..7) {
            for (y in 0..7) {
                if (Board[x][y].TYPE != "UNKNOWN") {
                    boardLocation[x][y].setImageResource(Board[x][y].toResource())
                    boardLocation[x][y].isClickable = Board[x][y].COLOR == MY_COLOR
                } else {
                    boardLocation[x][y].setImageResource(0)
                    boardLocation[x][y].isClickable = false
                }
            }
        }
    }

    private fun disabledBtn() {
        for ( i in boardLocation) {
            for ( j in i) {
                j.isClickable = false
            }
        }
    }
}