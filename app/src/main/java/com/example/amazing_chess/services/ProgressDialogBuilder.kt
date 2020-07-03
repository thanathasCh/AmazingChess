package com.example.amazing_chess.services

import android.app.ProgressDialog
import android.content.Context
import com.example.amazing_chess.R

class ProgressDialogBuilder(private val context: Context) {
    fun loadingBar(): ProgressDialog {
        val progress = ProgressDialog(context)

        with(progress) {
            setMessage(context.getString(R.string.loading))
            setCancelable(false)
        }

        return progress
    }
}