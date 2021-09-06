package com.bignerdranch.stu_1

import android.content.SharedPreferences
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedHelper(var  sharedPref: SharedPreferences) {
    private val editor: SharedPreferences.Editor = sharedPref.edit()
    private fun SharedPreferences.Editor.putParcelable(key: String, parcelable: Parcelable) {
        val json = Gson().toJson(parcelable)
        putString(key, json)
    }
    fun saveUsers(users:ArrayList<Person>){
        editor.clear()
        editor.putString(PersonViewModel.KEY_USER_LIST, Gson().toJson(users)).apply()
        editor.commit()
    }
    fun getUsers():ArrayList<Person>{
        var userList=ArrayList<Person>()
        val json = sharedPref.getString(PersonViewModel.KEY_USER_LIST,"[]")
        userList = Gson().fromJson(json, object : TypeToken<ArrayList<Person>>(){}.type)
        return userList
    }
}