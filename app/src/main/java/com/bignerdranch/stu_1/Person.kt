package com.bignerdranch.stu_1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Person(
    var id: String = UUID.randomUUID().toString(),
    var firstName: String = "",
    var lastName: String = "",
    var age: Int? = null
) : Parcelable
