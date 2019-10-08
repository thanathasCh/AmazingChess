package com.example.androidmidterm.Services

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