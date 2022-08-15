package com.example.lesttalk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lesttalk.databasse.entities.Messagem
import com.example.lesttalk.databinding.LayoutMessageSendBinding

class MessageAdapter(private val menssagem: MutableList<String>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(private val binding : LayoutMessageSendBinding) : RecyclerView.ViewHolder(binding.root) {
        val messagemItem = binding.txtMensagemReceived
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = LayoutMessageSendBinding.inflate(layout, parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = menssagem[position]
        holder.messagemItem.text = message

    }

    override fun getItemCount(): Int {
        return menssagem.size
    }

//    class DiffCallback : DiffUtil.ItemCallback<Messagem>() {
//        override fun areItemsTheSame(oldItem: Messagem, newItem: Messagem): Boolean {
//            return oldItem.messagem == newItem.messagem
//        }
//        override fun areContentsTheSame(oldItem: Messagem, newItem: Messagem): Boolean {
//            return oldItem == newItem
//        }
//
//    }
}