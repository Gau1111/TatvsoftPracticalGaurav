package info.softweb.gauravtatvsofttest.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import info.softweb.gauravtatvsofttest.R
import info.softweb.gauravtatvsofttest.model.FakeUser
import kotlinx.android.synthetic.main.row_grid.view.*
import kotlin.collections.ArrayList


class GridrecyclerviewAdapter(val context:Context, val userList: ArrayList<FakeUser>) : RecyclerView.Adapter<GridrecyclerviewAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_grid, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        for(i in 0 until userList.size)
        {
            if(userList[i].positionChecked)
            {
                if(i==position)
                {
                    holder.itemView.buttoNew.setBackgroundColor(context.resources.getColor(android.R.color.holo_blue_bright))
                    holder.itemView.buttoNew.isClickable=false
                }
            }
        }
        holder.bindItems(userList[position],position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateData(list:ArrayList<FakeUser>){
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: FakeUser, position: Int) {
            val button=itemView.findViewById<Button>(R.id.buttoNew)
            button.setText("Button "+user)

            if(position == 0)
            {
                button.postDelayed(Runnable {
                    kotlin.run {
                        button.setBackgroundColor(context.resources.getColor(android.R.color.holo_red_dark))
                    }
                },1000)
            }

            button.setOnClickListener {
                user.positionChecked=true
                button.isClickable=false
                button.setBackgroundColor(context.resources.getColor(android.R.color.holo_blue_bright))
            }
        }
    }
}