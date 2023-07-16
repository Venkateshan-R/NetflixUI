package com.example.suntask.data.repository

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(){

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    var token: String
        get() {
            return sharedPreferences.getString("token", "")!!
        }
        set(value) {
            sharedPreferences.edit().putString("token", value).apply()
        }

}