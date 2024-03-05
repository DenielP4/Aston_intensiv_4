package com.danielp4.fragmentmanager

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielp4.fragmentmanager.data.UserModel
import com.danielp4.fragmentmanager.utils.Constants

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val listUser = MutableLiveData<MutableList<UserModel>>()
    val currentPosition = MutableLiveData<Int>()

    fun init() {
        listUser.value = generateContact()
    }

    private fun generateContact(): MutableList<UserModel>? {
        val users = mutableListOf<UserModel>()
        for (i in 1..4) {
            val userModel = UserModel(
                image = getApplication<Application>().resources.getStringArray(R.array.list_image_url).random(),
                firstName = getApplication<Application>().resources.getStringArray(R.array.list_first_name).random(),
                secondName = getApplication<Application>().resources.getStringArray(R.array.list_second_name).random(),
                phoneNumber = getApplication<Application>().resources.getStringArray(R.array.list_phone_number).random()
            )
            users.add(userModel)
        }
        return users
    }

}