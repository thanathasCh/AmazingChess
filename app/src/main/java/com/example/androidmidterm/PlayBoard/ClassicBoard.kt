package com.example.androidmidterm.PlayBoard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.toResource
import kotlinx.android.synthetic.main.activity_classic_board.*

class ClassicBoard : Board() {
    private lateinit var boardLocation: Array<Array<ImageView>>
    val Board = super.MainBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_board)

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


        val btnClicked = View.OnClickListener {
            
        }
    }

    private fun updateBoardUI() {
        for(x in 0..7) {
            for (y in 0..7) {
                if (Board[x][y] != null) {
                    boardLocation[x][y].setImageResource(Board[x][y]!!.toResource())
                    boardLocation[x][y].isClickable = true
                } else {
                    boardLocation[x][y].isClickable = false
                }
            }
        }
    }

    /*
    View.OnClickListener myOnlyhandler = new View.OnClickListener() {
  public void onClick(View v) {
      switch(v.getId()) {
        case R.id.b1:
          // it was the first button
          break;
        case R.id.b2:
          // it was the second button
          break;
      }
  }
}
     */
}