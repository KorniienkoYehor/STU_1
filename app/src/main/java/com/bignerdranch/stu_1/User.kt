package com.bignerdranch.stu_1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val firstName: String?,
    val lastName: String?,
    var age: Int? = null
) {
    companion object {
        fun from(person: Person) = User(
            id = person.id,
            firstName = person.firstName,
            lastName = person.lastName,
            age = person.age
        )
    }
}
