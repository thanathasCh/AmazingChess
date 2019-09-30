package com.example.androidmidterm.Services

class Mapper {
    fun UserModel.toUserViewModel() = UserViewModel (
        Id = Id,
        Name = Name,
        FullName = "$FirstName $LastName",
        ImageUrl = ImageURL,
        Score = Score
    )

    fun GameRoomModel.toGameRoomViewModel() = GameRoomViewModel (
        Id = Id,
        Name = Name,
        Status = when (StatusByte) {
            0 -> "Ended"
            1 -> "Waiting"
            2 -> "Playing"
            else -> "Unknown"
        }
    )
}