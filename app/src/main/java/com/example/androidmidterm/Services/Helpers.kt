package com.example.androidmidterm.Services

fun ByteArray.toHexString() = joinToString("") {"%02x".format(it)}

fun encrypt(password: String) = password.toByteArray().toHexString()