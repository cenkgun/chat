package com.cenkgun.presentation.ui.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cenkgun.presentation.databinding.ItemReceivedMessageBinding
import com.cenkgun.presentation.databinding.ItemSentMessageBinding
import com.cenkgun.presentation.models.MessageModel
import com.cenkgun.presentation.utils.getHumanReadableDate
import com.cenkgun.presentation.utils.load

class MessageAdapter(
    private val userId: String
) : ListAdapter<MessageModel, RecyclerView.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_SENT) {
            return SentMessageViewHolder(
                ItemSentMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        return ReceivedMessageViewHolder(
            ItemReceivedMessageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is SentMessageViewHolder) {
            holder.bind(item)
        } else if (holder is ReceivedMessageViewHolder) {
            holder.bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        if (item.user.id == userId) {
            return VIEW_TYPE_SENT
        }
        return VIEW_TYPE_RECEIVED
    }

    class SentMessageViewHolder(private val binding: ItemSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageModel) {
            binding.apply {
                nicknameText.text = message.user.nickname
                messageText.text = message.text
                dateText.text = message.timestamp.getHumanReadableDate()
                avatarImage.load(message.user.avatarURL)
            }
        }
    }

    class ReceivedMessageViewHolder(private val binding: ItemReceivedMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageModel) {
            binding.apply {
                nicknameText.text = message.user.nickname
                messageText.text = message.text
                dateText.text = message.timestamp.getHumanReadableDate()
                avatarImage.load(message.user.avatarURL)
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<MessageModel>() {
        override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean =
            (oldItem.text == newItem.text)
                && (oldItem.timestamp == newItem.timestamp)
                && (oldItem.text == newItem.text)
                && (oldItem.user.id == newItem.user.id)

        override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean =
            oldItem == newItem
    }

    companion object {
        private const val VIEW_TYPE_SENT = 0
        private const val VIEW_TYPE_RECEIVED = 1
    }
}
