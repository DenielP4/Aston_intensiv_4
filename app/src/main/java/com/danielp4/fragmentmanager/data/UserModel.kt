package com.danielp4.fragmentmanager.data

import java.io.Serializable

data class UserModel(
    val image: String,
    val firstName: String,
    val secondName: String,
    val phoneNumber: String
) : Serializable
