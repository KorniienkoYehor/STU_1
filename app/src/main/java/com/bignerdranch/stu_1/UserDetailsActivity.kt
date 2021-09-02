package com.bignerdranch.stu_1


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.bignerdranch.stu_1.MainActivity.Companion.KEY_PERSONE
import kotlinx.android.synthetic.main.user_changes.*
import java.util.*


class UserDetailsActivity : Activity() {

    private lateinit var btnSave: Button
    private lateinit var person:Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_changes)

        person = intent.getParcelableExtra(KEY_PERSONE)?: Person()
        name.setText(person.firstName)
        surname.setText(person.lastName)
        age.setText(person.age.toString())

        btnSave = findViewById(R.id.btnSave)
        btnSave.setOnClickListener {
            person.firstName = name.text.toString()
            person.lastName = surname?.text.toString()
            person.age = age?.text.toString().toInt()
            val intent = Intent()
            intent.putExtra(KEY_USER, person)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    companion object {
        const val KEY_USER = "KEY_USER"
        fun getIntent(context: Context) = Intent(context, UserDetailsActivity::class.java)

        fun getIntentEdit(context: Context,person: Person):Intent{
            val child = Intent(context, UserDetailsActivity::class.java)
            child.putExtra(KEY_PERSONE, person)

            return child
        }

        fun getIntentEdit1(context: Context,person: Person)= Intent(context, UserDetailsActivity::class.java)
            .apply {
                putExtra(KEY_PERSONE, person)
            }
    }
}