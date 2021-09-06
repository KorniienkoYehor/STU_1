package com.bignerdranch.stu_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_user.view.*

class Adapter(
    private val listener: OnItemClickListener,
    private val listenerLong: OnItemLongClickListener
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var dataSet= ArrayList<Person>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_user, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


    fun addUser(person:Person){

        dataSet.add(person)
        notifyItemChanged(dataSet.indexOf(person))
    }

    fun changeUser(person:Person){
        val position: Int=getPosition(person)
        dataSet.set(position,person)
        notifyItemChanged(position)
    }

    fun getCurrentItem(position:Int):Person{
        return dataSet[position]
    }

    fun removeItem(position: Int){
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }
    fun getDataSet(): ArrayList<Person> = dataSet
    fun changeDataSet(list:ArrayList<Person>){
        dataSet.clear()
        dataSet.addAll(list)
        notifyDataSetChanged()
    }
    fun getPosition(person: Person) : Int{
        for((index, element) in dataSet.withIndex()){
            if(element.id==person.id){
                return index
            }
        }
        return -1
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        init {

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
            itemView.setOnLongClickListener {
                val position: Int = adapterPosition

                listenerLong.onItemLongClick(getCurrentItem(position),itemView)

            }
        }

        fun bind(person: Person) {
            itemView.user_name.text = person.firstName
            itemView.user_surname.text = person.lastName
            itemView.user_age.text = person.age.toString()
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(person: Person,view: View): Boolean
    }

}



