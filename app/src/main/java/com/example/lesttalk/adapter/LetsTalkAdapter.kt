package com.example.lesttalk.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lesttalk.databinding.LayoutRowBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LetsTalkAdapter(private val user: MutableList<String>) : RecyclerView.Adapter<LetsTalkAdapter.LetsTalkViewHolder>() {

    private lateinit var clickListener : onItemClickListener

    interface onItemClickListener {
         fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        clickListener = listener
    }

    class LetsTalkViewHolder(binding : LayoutRowBinding, listener : onItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        val txtName = binding.txtName

        init {
            txtName.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : LetsTalkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutRowBinding.inflate(inflater, parent, false)
        return LetsTalkViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: LetsTalkViewHolder, position: Int) {
        val currentItem = user[position]
        holder.txtName.text = currentItem
    }

    override fun getItemCount(): Int = user.size

//    class DiffCallback : DiffUtil.ItemCallback<User>() {
//        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
//        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
//
//    }


}