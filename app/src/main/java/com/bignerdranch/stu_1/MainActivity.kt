package com.bignerdranch.stu_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener,
    Adapter.OnItemLongClickListener {
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var person: Person
   // private var positionItemL: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        adapter = Adapter(this, this)

        recyclerView.adapter = adapter


        val fab: View = findViewById(R.id.add_user)
        fab.setOnClickListener { view ->

            startActivityForResult(UserDetailsActivity.getIntent(this), REQ_CODE_CHILD)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(KEY_SAVE, adapter.getDataSet())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)

        val dataSet = savedInstanceState.getParcelableArrayList<Person>(KEY_SAVE)
        if (dataSet != null) {
            adapter.changeDataSet(dataSet)
        }

    }

    override fun onItemClick(position: Int) {
        startActivityForResult(
            UserDetailsActivity.getIntentEdit(this,adapter.getCurrentItem(position)),
            REQ_CODE_CHILD_CHANGE_CURRENT)
    }

    override fun onItemLongClick(newperson: Person, view: View): Boolean {
        person=newperson
        view.setOnCreateContextMenuListener(this)
        return false
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // TODO Auto-generated method stub
        when (item.getItemId()) {
            R.id.delete -> {
                adapter.removeItem(adapter.getPosition(person))
            }
            R.id.cancel -> {
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQ_CODE_CHILD -> {
                    val newPerson = data?.getParcelableExtra<Person>(UserDetailsActivity.KEY_USER)
                    if (newPerson != null) {
                        adapter.addUser(newPerson)
                    }
                }
                REQ_CODE_CHILD_CHANGE_CURRENT -> {
                    val edit_person = data?.getParcelableExtra<Person>(UserDetailsActivity.KEY_USER)
                    if (edit_person != null) {
                        adapter.changeUser(edit_person)
                    }
                }
            }
        }


    }

    companion object {
        private const val REQ_CODE_CHILD = 1
        private const val REQ_CODE_CHILD_CHANGE_CURRENT = 2
        const val KEY_PERSONE = "PERSON"
        const val KEY_SAVE = "KEY"
    }

}