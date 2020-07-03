package com.example.amazing_chess.services

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import com.example.amazing_chess.R

class AlertDialogBuilder(private val context: Context) {
    private val mHandler = Handler()

    fun showInfo(title: Int, message: Int) {
        mHandler.post {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button) { _, _ -> }
                .setCancelable(false)
                .show()
        }
    }

    fun showWarning(title: Int, message: Int) {
        mHandler.post {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button) { _, _ -> }
                .setCancelable(false)
                .show()
        }
    }

    fun showInfo(title: Int, message: Int, action: () -> Unit) {
        mHandler.post {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button) { _, _ ->
                    action()
                }
                .setCancelable(false)
                .show()
        }
    }

    fun showWarning(title: Int, message: Int, action: () -> Unit) {
        mHandler.post {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button) { _, _ ->
                    action()
                }
                .setOnCancelListener { action() }
                .show()
        }
    }
}