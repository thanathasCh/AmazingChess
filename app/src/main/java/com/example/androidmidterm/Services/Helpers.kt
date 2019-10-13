package com.example.androidmidterm.Services

import android.annotation.SuppressLint
import android.app.Activity
import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.androidmidterm.ChessPieces.*
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.R

fun isSubBoard(pos: Pair<Int, Int>) = ((pos.first % 2 == 0) == (pos.second % 2 == 0))

var global_board = R.layout.activity_classic_board

var global_playing_status = 1

fun ByteArray.toHexString() = joinToString("") {"%02x".format(it)}

fun String.encrypt() = toByteArray().toHexString()

val MY_COLOR = (if (global_playing_status == 0) "BLACK" else "WHITE")

val OP_COLOR = (if (global_playing_status == 0) "WHITE" else "BLACK")

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
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
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
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
        arrayOf<Pieces>(Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown(), Unknown()),
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
    mAlertDialog.setIcon(R.drawable.p_momo)

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

fun Pair<Int, Int>.isExceedBoard() = !(this.first in 0..7 && this.second in 0..7)