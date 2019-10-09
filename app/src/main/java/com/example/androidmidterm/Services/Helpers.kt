package com.example.androidmidterm.Services

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.androidmidterm.R

var global_board = R.layout.activity_classic_board

fun ByteArray.toHexString() = joinToString("") {"%02x".format(it)}

fun String.encrypt() = toByteArray().toHexString()

fun createBoardWhite(): Array<Array<out Pieces?>> =
    arrayOf(
        arrayOf<Pieces?>(Pieces(2, 2), Pieces(3, 2), Pieces(4, 2), Pieces(6, 2), Pieces(5, 2), Pieces(4, 2), Pieces(3, 2), Pieces(2, 2)),
        arrayOf<Pieces?>(Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2)),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1)),
        arrayOf(Pieces(2, 1), Pieces(3, 1), Pieces(4, 1), Pieces(5, 1), Pieces(6, 1), Pieces(4, 1), Pieces(3, 1), Pieces(2,1))
    )

fun createBoardBlack(): Array<Array<out Pieces?>> =
    arrayOf(
        arrayOf<Pieces?>(Pieces(2, 1), Pieces(3, 1), Pieces(4, 1), Pieces(6, 1), Pieces(5, 1), Pieces(4, 1), Pieces(3, 1), Pieces(2, 1)),
        arrayOf<Pieces?>(Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1), Pieces(1, 1)),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(null, null, null, null, null, null, null, null),
        arrayOf<Pieces?>(Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2), Pieces(1, 2)),
        arrayOf(Pieces(2, 2), Pieces(3, 2), Pieces(4, 2), Pieces(5, 2), Pieces(6, 2), Pieces(4, 2), Pieces(3, 2), Pieces(2,2))
    )

fun walk(Board: Array<Array<out Pieces?>>, x: Int, y: Int) {

}

fun endGame() {

}

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