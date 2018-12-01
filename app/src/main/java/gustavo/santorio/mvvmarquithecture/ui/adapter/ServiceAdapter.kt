package gustavo.santorio.mvvmarquithecture.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gustavo.santorio.mvvmarquithecture.R
import gustavo.santorio.mvvmarquithecture.model.ServiceVO
import kotlinx.android.synthetic.main.item_service.view.*

class ServiceAdapter(val context: Context, val servicesVO : List<ServiceVO>) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    private var serviceSelectedListener : ServiceSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder =
            ServiceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_service, parent, false))

    override fun getItemCount(): Int = servicesVO.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.tv_name.text = servicesVO[position].name
        holder.tv_time.text = servicesVO[position].time.toString() + " minutos"
        holder.tv_value.text = "R$ " + servicesVO[position].value.toString()

        when(servicesVO[position].tipe){
            1 -> {
                holder.iv_image.setImageResource(R.drawable.ic_yellow)
            }
            2 -> {
                holder.iv_image.setImageResource(R.drawable.ic_grin)
            }
            3 -> {
                holder.iv_image.setImageResource(R.drawable.ic_uber)
            }
            4 -> {
                holder.iv_image.setImageResource(R.drawable.ic_99)
            }
            5 -> {
                holder.iv_image.setImageResource(R.drawable.ic_4_move)
            }
            6 -> {
                holder.iv_image.setImageResource(R.drawable.ic_yellow)
            }
            7 -> {
                holder.iv_image.setImageResource(R.drawable.ic_yellow)
            }
            8 -> {
                holder.iv_image.setImageResource(R.drawable.ic_cade_meu_on)
            }
            9 -> {
                holder.iv_image.setImageResource(R.drawable.ic_moovit)
            }
            10 -> {
                holder.iv_image.setImageResource(R.drawable.ic_yellow)
            }
        }

        holder.view.setOnClickListener {
            serviceSelectedListener?.onServiceSelected(servicesVO[position].tipe!!)
        }
    }

    class ServiceViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val iv_image = view.iv_image
        val tv_name = view.tv_name
        val tv_time = view.tv_time
        val tv_value = view.tv_value
    }

    fun setServiceSelectedListener(serviceSelectedListener: ServiceSelectedListener){
        this.serviceSelectedListener = serviceSelectedListener
    }

    interface ServiceSelectedListener {
        fun onServiceSelected(service : Int)
    }
}