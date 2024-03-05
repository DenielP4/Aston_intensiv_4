package com.danielp4.fragmentmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielp4.fragmentmanager.R
import com.danielp4.fragmentmanager.databinding.UserItemBinding
import com.danielp4.fragmentmanager.data.UserModel
import com.squareup.picasso.Picasso

class UserAdapter(
    val listener: Listener
) : ListAdapter<UserModel, UserAdapter.UserHolder>(UserDiffCallback()) {

    class UserHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = UserItemBinding.bind(item)
        fun bind(user: UserModel, listener: Listener) = with(binding) {
            Picasso.get()
                .load(user.image)
                .into(imgUser)
            tvFirstName.text = user.firstName
            tvSecondName.text = user.secondName
            tvPhoneNumber.text = user.phoneNumber
            itemView.setOnClickListener {
                listener.onClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class UserDiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem== newItem
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onClick(user: UserModel)
    }
}
