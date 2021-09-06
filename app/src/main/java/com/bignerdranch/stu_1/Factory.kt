package com.bignerdranch.stu_1

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.SharedPreferences

class Factory(context:Context) {

    var sharedpreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    var sharedHelper=SharedHelper(sharedpreferences)
    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PersonViewModel(sharedHelper) as T
        }
    }
    companion object{
        const val PREFERENCE_KEY="preference_key"
    }
}