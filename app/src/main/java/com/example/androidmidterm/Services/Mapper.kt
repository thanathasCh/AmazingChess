package com.example.androidmidterm.Services

class Mapper {
    fun User.toUserView() = UserView (
        Id = Id,
        Name = Name,
        FullName = "$FirstName $LastName",
        ImageUrl = ImageURL

    )
}