package com.example.amazing_chess.services

import android.app.ProgressDialog
import android.content.Context

class ProgressDialogBuilder(private val context: Context) {
    fun loadingBar(message: Int): ProgressDialog {
        val progress = ProgressDialog(context)

        with(progress) {
            setMessage(context.getString(message))
            setCancelable(false)
        }

        return progress
    }
}