package com.bignerdranch.stu_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener,
    Adapter.OnItemLongClickListener {
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var person: Person
    private lateinit var viewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var factory=Factory(this)
        recyclerView = findViewById(R.id.recyclerview)
        adapter = Adapter(this, this)

        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, factory.factory).get(PersonViewModel::class.java)

        viewModel.getPersons()
        viewModel.usersLiveData.observe(this)  { users ->
            // update UI
            adapter.changeDataSet(users)
        }

        val fab: View = findViewById(R.id.add_user)
        fab.setOnClickListener { view ->

            startActivityForResult(UserDetailsActivity.getIntent(this), REQ_CODE_CHILD)

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
                viewModel.delete(person)
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
                        viewModel.addUser(newPerson)
                    }
                }
                REQ_CODE_CHILD_CHANGE_CURRENT -> {
                    val edit_person = data?.getParcelableExtra<Person>(UserDetailsActivity.KEY_USER)
                    if (edit_person != null) {
                        adapter.changeUser(edit_person)
                        viewModel.changeUser(edit_person)
                    }
                }
            }
        }


    }

    companion object {
        private const val REQ_CODE_CHILD = 1
        private const val REQ_CODE_CHILD_CHANGE_CURRENT = 2
        const val KEY_PERSONE = "PERSON"
    }

}