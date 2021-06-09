package ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.R
import ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.data.Chat

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private var listChat = ArrayList<Chat>()

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(chat: Chat){
            val llUserMsg = itemView.findViewById<LinearLayout>(R.id.tv_msg_user)
            val tvBotMsg = itemView.findViewById<TextView>(R.id.tv_msg_bot)
            val tvDateTaken = itemView.findViewById<TextView>(R.id.tv_date_taken)

            if (chat.isResponse){
                tvBotMsg.visibility = View.VISIBLE
                tvBotMsg.text = chat.response
            } else {
                llUserMsg.visibility = View.VISIBLE
                tvDateTaken.text = chat.dateTime
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent,false)

        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        //dummy
        holder.bindTo(listChat[position])
    }

    override fun getItemCount(): Int {
        return listChat.size
    }

    fun setChat(chats: List<Chat>){
        listChat.addAll(chats)
    }
}