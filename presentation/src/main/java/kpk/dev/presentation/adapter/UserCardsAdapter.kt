package kpk.dev.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user_data.view.*
import kpk.dev.domain.poko.UserData
import kpk.dev.presentation.R

class UserCardsAdapter(private val context: Context): RecyclerView.Adapter<UserCardsAdapter.UserCardViewHolder>() {

    private val usersData:MutableList<UserData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardViewHolder = UserCardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_data, parent, false))

    override fun getItemCount(): Int = usersData.size


    override fun onBindViewHolder(holder: UserCardViewHolder, position: Int) {
        holder.bind(usersData[position])
    }

    fun addAllData(data: List<UserData>) {
        usersData.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: UserData?) {
        if (data != null) {
            usersData.add(data)
        }
        notifyItemInserted(usersData.size - 1)
    }

    class UserCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(userData: UserData) = with(itemView){
            this.tv_name.text = userData.name
            this.tv_address.text = userData.address
        }
    }
}