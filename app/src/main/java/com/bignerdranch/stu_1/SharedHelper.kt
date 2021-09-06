package com.bignerdranch.stu_1

import android.content.SharedPreferences
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedHelper(var sharedPref: SharedPreferences) : StorageHelper{
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    override fun saveUsers(users:ArrayList<Person>){
        editor.putString(PersonViewModel.KEY_USER_LIST, Gson().toJson(users))
        editor.commit()
    }
    override fun getUsers():ArrayList<Person>{
        val json = sharedPref.getString(PersonViewModel.KEY_USER_LIST,"[]")
        val userList:ArrayList<Person> = Gson().fromJson(json, object : TypeToken<ArrayList<Person>>(){}.type)
        return userList
    }
}

