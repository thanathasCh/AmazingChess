package com.example.androidmidterm.Services

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.androidmidterm.ChessPieces.*
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.R

var global_board = R.layout.activity_classic_board

fun ByteArray.toHexString() = joinToString("") {"%02x".format(it)}

fun String.encrypt() = toByteArray().toHexString()

fun createBoardWhite(): Array<Array<out Pieces?>> =
    arrayOf(
        arrayOf<Pieces?>(
            Rook("BLACK"),
            Knight("BLACK"),
            Bishop("BLACK"),
            King("BLACK"),
            Queen("BLACK"),
            Bishop("BLACK"),
            Knight("BLACK"),
            Rook("BLACK")
        ),
        arrayOf<Pieces?>(
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK"),
            Pawn("BLACK")
        ),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(
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

fun createBoardBlack(): Array<Array<out Pieces?>> =
    arrayOf(
        arrayOf<Pieces?>(
            Rook("WHITE"),
            Knight("WHITE"),
            Bishop("WHITE"),
            King("WHITE"),
            Queen("WHITE"),
            Bishop("WHITE"),
            Knight("WHITE"),
            Rook("WHITE")
        ),
        arrayOf<Pieces?>(
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE"),
            Pawn("WHITE")
        ),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(
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