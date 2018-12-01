package gustavo.santorio.mvvmarquithecture.ui.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gustavo.santorio.mvvmarquithecture.R
import gustavo.santorio.mvvmarquithecture.model.MessageVO
import kotlinx.android.synthetic.main.item_message.view.*
import kotlinx.android.synthetic.main.item_text.view.*

class MessageAdapter(val context : Context) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    companion object {
        val TYPE_TEXT = 0
        val TYPE_MESSAGE = 1
        val TYPE_BOT = 2
    }

    var serviceSelectedListener : ServiceAdapter.ServiceSelectedListener? = null

    var messagesVO : MutableList<MessageVO> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return messagesVO[position].messageType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        when(viewType){
            TYPE_TEXT -> {
                return TextViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false))
            }
            TYPE_MESSAGE -> {
                return MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_message, parent, false))
            }

            TYPE_BOT -> {
                return TextViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bot, parent, false))
            }
            else -> {
                return TextViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text, parent, false))
            }
        }

    }

    override fun getItemCount(): Int {
        return messagesVO.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        if(holder is TextViewHolder)
            holder.tv_text.text = messagesVO[position].message
        else{
            holder.rv_service?.layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false){
                override fun canScrollHorizontally(): Boolean  = false
            }
            holder.rv_service.adapter = ServiceAdapter(context, messagesVO[position].servicesVO!!)
            serviceSelectedListener?.let {
                (holder.rv_service.adapter as ServiceAdapter).setServiceSelectedListener(it)
            }

            when(messagesVO[position].profileType) {
                1 -> {
                    holder.tv_title.text = "Transporte Sustentável"
                    holder.iv_image.setImageResource(R.drawable.ic_trans_sust)
                }
                2 -> {
                    holder.tv_title.text = "Transporte Privado"
                    holder.iv_image.setImageResource(R.drawable.ic_trans_privado)
                }
                3 -> {
                    holder.tv_title.text = "Transporte Coletivo"
                    holder.iv_image.setImageResource(R.drawable.ic_trans_pub)
                }
            }
        }
    }

    fun addMessage(message : String, messageType : Int){
        val messageVO = MessageVO()
        messageVO.message = message
        messageVO.messageType = messageType
        messagesVO.add(messageVO)
        notifyDataSetChanged()
    }

    fun addAll(messagesVO : MutableList<MessageVO>){
        this.messagesVO.addAll(messagesVO)
        notifyDataSetChanged()
    }

    open class MessageViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val rv_service = view.rv_service
        val iv_image= view.iv_image
        val tv_title = view.tv_title
    }
    class TextViewHolder(view : View) : MessageViewHolder(view){
        val tv_text = view.tv_text
    }
}