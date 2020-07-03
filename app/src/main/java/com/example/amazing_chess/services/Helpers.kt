package com.example.amazing_chess.services

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.example.amazing_chess.R
import com.example.amazing_chess.models.chessPieces.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

fun isSubBoard(pos: Pair<Int, Int>) = ((pos.first % 2 == 0) == (pos.second % 2 == 0))

var USER_ID = ""

var GAME_ID = ""

var global_board = R.layout.activity_classic_board

var global_player_status = 0

var my_turn = false

fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }

fun String.encrypt() = toByteArray().toHexString()

fun MY_COLOR(): String {
    if (global_player_status == 0) {
        return "BLACK"
    } else {
        return "WHITE"
    }
}

fun OP_COLOR(): String {
    if (global_player_status == 0) {
        return "WHITE"
    } else {
        return "BLACK"
    }
}

fun MY_MOVE(): String {
    if (global_player_status == 0) {
        return "Move1"
    } else {
        return "Move2"
    }
}

fun OP_MOVE(): String {
    if (global_player_status == 0) {
        return "Move2"
    } else {
        return "Move1"
    }
}

fun Pieces.toResource(): Int {
    if (COLOR == "BLACK") {
        when (TYPE) {
            "PAWN" -> return R.drawable.bp
            "ROOK" -> return R.drawable.br
            "KNIGHT" -> return R.drawable.bn
            "BISHOP" -> return R.drawable.bb
            "QUEEN" -> return R.drawable.bq
            else -> return R.drawable.bk
        }
    } else {
        when (TYPE) {
            "PAWN" -> return R.drawable.wp
            "ROOK" -> return R.drawable.wr
            "KNIGHT" -> return R.drawable.wn
            "BISHOP" -> return R.drawable.wb
            "QUEEN" -> return R.drawable.wq
            else -> return R.drawable.wk
        }
    }
}

fun createBoardWhite(): Array<Array<Pieces>> =
    arrayOf(
        arrayOf(
            Rook("BLACK"),
            Knight("BLACK"),
            Bishop("BLACK"),
            King("BLACK"),
            Queen("BLACK"),
            Bishop("BLACK"),
            Knight("BLACK"),
            Rook("BLACK")
        ),
        arrayOf<Pieces>(
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK")
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE")
        ),
        arrayOf(
            Rook("WHITE"),
            Knight("WHITE"),
            Bishop("WHITE"),
            Queen("WHITE"),
            King("WHITE"),
            Bishop("WHITE"),
            Knight("WHITE"),
            Rook("WHITE")
        )
    )

fun createBoardBlack(): Array<Array<Pieces>> =
    arrayOf(
        arrayOf(
            Rook("WHITE"),
            Knight("WHITE"),
            Bishop("WHITE"),
            King("WHITE"),
            Queen("WHITE"),
            Bishop("WHITE"),
            Knight("WHITE"),
            Rook("WHITE")
        ),
        arrayOf<Pieces>(
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE")
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown(),
            Unknown()
        ),
        arrayOf<Pieces>(
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK")
        ),
        arrayOf(
            Rook("BLACK"),
            Knight("BLACK"),
            Bishop("BLACK"),
            Queen("BLACK"),
            King("BLACK"),
            Bishop("BLACK"),
            Knight("BLACK"),
            Rook("BLACK")
        )
    )

fun warningBox(a: Activity, title: String, context: String) {
    val mAlertDialog = AlertDialog.Builder(a)
    mAlertDialog.setTitle(title)
    mAlertDialog.setMessage(context)
    mAlertDialog.setIcon(R.drawable.pop_up)

    mAlertDialog.setNegativeButton("OK") { dialog, _ ->
        dialog.dismiss()
    }
    mAlertDialog.show()
}

val PAWN = arrayOf(R.drawable.bp, R.drawable.wp)
val BISHOP = arrayOf(R.drawable.bb, R.drawable.wb)
val ROOK = arrayOf(R.drawable.br, R.drawable.wr)
val KNIGHT = arrayOf(R.drawable.bn, R.drawable.wn)
val QUEEN = arrayOf(R.drawable.bq, R.drawable.wq)
val KING = arrayOf(R.drawable.bk, R.drawable.wk)

fun Array<Array<ImageView>>.indexOf2D(view: View): Pair<Int, Int> {
    for (x in indices) {
        for (y in this[x].indices) {
            if (this[x][y].id == view.id) {
                return Pair(x, y)
            }
        }
    }

    return Pair(-1, -1)
}

fun Array<Array<Pieces>>.indexOf2D(p: Pieces): Pair<Int, Int> {
    for (x in indices) {
        for (y in this[x].indices) {
            if (this[x][y].TYPE == p.TYPE && this[x][y].COLOR == p.COLOR) {
                return Pair(x, y)
            }
        }
    }

    return Pair(-1, -1)
}

fun Array<Array<Pieces>>.containsPieces(p: Pieces): Boolean {
    val pair = indexOf2D(p)

    return (pair.first >= 0 && pair.second >= 0)
}

fun Pair<Int, Int>.isExceedBoard() = !(this.first in 0..7 && this.second in 0..7)

fun NOT_YOUR_TURN(a: Activity) {
    Toast.makeText(a, "This is not your turn!!", Toast.LENGTH_SHORT).show()
}

fun YOUR_TURN(a: Activity) {
    Toast.makeText(a, "Your turn!!", Toast.LENGTH_SHORT).show()
}

fun OP_TURN(a: Activity) {
    Toast.makeText(a, "Opponent's turn!!", Toast.LENGTH_SHORT).show()
}

fun updateScore(p: Int) {
    val db = DbContext()
//    val data = db.child(USER_ID)
    val data = db.Users.child(USER_ID)
    data.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {}

        override fun onDataChange(p0: DataSnapshot) {
            val n = p0.getValue(UserModel::class.java)!!.Score

            db.Users.child(USER_ID).child("Score").setValue(n.plus(p))
        }

    })
}

fun flip(old: List<String>): List<String> {
    return listOf(
        (7 - old[0].toInt()).toString(), (7 - old[1].toInt()).toString(),
        (7 - old[2].toInt()).toString(), (7 - old[3].toInt()).toString()
    )
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}