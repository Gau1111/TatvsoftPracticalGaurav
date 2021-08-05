package info.softweb.gauravtatvsofttest.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import info.softweb.gauravtatvsofttest.R
import info.softweb.gauravtatvsofttest.model.Data
import info.softweb.gauravtatvsofttest.model.UserListResponseModel
import kotlinx.android.synthetic.main.row_user.view.*
import kotlin.collections.ArrayList


class UserrecyclerviewAdapter(val context:Context, val userList: ArrayList<Data>) : RecyclerView.Adapter<UserrecyclerviewAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_grid, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateData(list:ArrayList<Data>){
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Data) {
            itemView.name.text="${user.firstName} ${user.lastName}"
            itemView.txtEmail.text=user.email
        }
    }
}