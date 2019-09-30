package com.example.androidmidterm.Providers

interface IGame {
    public abstract fun createRoom(): Boolean

    public abstract fun findRoom(roomId: Int): Boolean
}