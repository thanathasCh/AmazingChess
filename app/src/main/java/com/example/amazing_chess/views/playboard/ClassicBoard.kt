package com.example.amazing_chess.views.playboard

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.amazing_chess.R
import com.example.amazing_chess.models.chess_pieces.King
import com.example.amazing_chess.services.*
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_classic_board.*

class ClassicBoard : Board() {
    private lateinit var boardLocation: Array<Array<ImageView>>
    private lateinit var data: DatabaseReference
    private lateinit var listener: ChildEventListener
    private val MAIN = R.drawable.bg_classic_main_board
    private val SUB = R.drawable.bg_classic_sub_board
    private val db = DbContext()
    private var isFirst = true
    val Board = super.MainBoard
    var isRed = false
    var redList = listOf<Pair<Int, Int>>()
    var pos = Pair(-1, -1)

    fun btnClicked(view: View) {
        if (my_turn) {
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
                    sendMove(pos, pair)
                    updateBoardUI()
                    my_turn = false
                }
                for (i in redList) {
                    boardLocation[i.first][i.second].setBackgroundResource(if (isSubBoard(i)) SUB else MAIN)
                }
                isRed = false
            }
        } else {
            NOT_YOUR_TURN(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_board)

        data = db.GameRooms.child(GAME_ID).child(OP_MOVE())
        listener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                if (dataSnapshot.value != "GiveUp") {
                    val move = dataSnapshot.getValue().toString()
                    var move_list = move.split(",")

                    if (!my_turn) {
                        move_list = flip(move_list)
                    }

                    op_move(
                        Pair(move_list[0].toInt(), move_list[1].toInt()),
                        Pair(move_list[2].toInt(), move_list[3].toInt())
                    )
                    updateBoardUI()
                    my_turn = !my_turn
                } else {
                    updateScore(10)
                    Toast.makeText(this@ClassicBoard, "YOU WIN!!, YOUR OPPONENT GAVE UP", Toast.LENGTH_SHORT).show()
                    endGame()
                }
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {}

            override fun onChildRemoved(p0: DataSnapshot) {}
        }

        data.addChildEventListener(listener)

        if (isFirst) {
            boardLocation = arrayOf(
                arrayOf(a1, a2, a3, a4, a5, a6, a7, a8),
                arrayOf(b1, b2, b3, b4, b5, b6, b7, b8),
                arrayOf(c1, c2, c3, c4, c5, c6, c7, c8),
                arrayOf(d1, d2, d3, d4, d5, d6, d7, d8),
                arrayOf(e1, e2, e3, e4, e5, e6, e7, e8),
                arrayOf(f1, f2, f3, f4, f5, f6, f7, f8),
                arrayOf(g1, g2, g3, g4, g5, g6, g7, g8),
                arrayOf(h1, h2, h3, h4, h5, h6, h7, h8)
            )
            updateBoardUI()
            isFirst = false
        }

        tvGiveUp.setOnClickListener {
            give_up()
        }
    }

    private fun updateBoardUI() {
        for (x in 0..7) {
            for (y in 0..7) {
                if (Board[x][y].TYPE != "UNKNOWN") {
                    boardLocation[x][y].setImageResource(Board[x][y].toResource())
                    boardLocation[x][y].isClickable = Board[x][y].COLOR == MY_COLOR()
                } else {
                    boardLocation[x][y].setImageResource(0)
                    boardLocation[x][y].isClickable = false
                }
            }
        }

        if (!Board.containsPieces(King(MY_COLOR()))) {
            updateScore(-10)
            Toast.makeText(this@ClassicBoard, "YOU LOST!!", Toast.LENGTH_SHORT).show()
            endGame()
        } else if (!Board.containsPieces(King(OP_COLOR()))) {
            updateScore(10)
            Toast.makeText(this@ClassicBoard, "YOU WIN!!", Toast.LENGTH_SHORT).show()
            endGame()
        }
    }

    private fun sendMove(pos: Pair<Int, Int>, des: Pair<Int, Int>) {
        val move = "${pos.first},${pos.second},${des.first},${des.second}"
        val update = HashMap<String, Any>()
        update["/$GAME_ID/${MY_MOVE()}/value"] = move
        db.GameRooms.updateChildren(update)
    }

    private fun endGame() {
        data.removeEventListener(listener)
        finish()
    }

    private fun give_up() {
        val mes = "GiveUp"
        val update = HashMap<String, Any>()
        update["/$GAME_ID/${MY_MOVE()}/value"] = mes
        db.GameRooms.updateChildren(update)

        updateScore(-10)
        Toast.makeText(this@ClassicBoard, "YOU LOST!!, YOU GAVE UP", Toast.LENGTH_SHORT).show()
        endGame()
    }
}