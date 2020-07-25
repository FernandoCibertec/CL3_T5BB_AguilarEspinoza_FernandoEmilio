package com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.R
import com.example.cl3_t5bb_aguilarespinoza_fernandoemilio.pojo.ObjSERVICIO
import kotlinx.android.synthetic.main.servicio.view.*

class AdaptadorServicio  (val listaInterna:List<ObjSERVICIO>, val clickListener: (ObjSERVICIO) -> Unit):
    RecyclerView.Adapter<AdaptadorServicio.AdaptadorServicioViewHolder>() {
    var onItemClick: ((ObjSERVICIO) -> Unit)? = null
    public class AdaptadorServicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindItems(oObjSERVICIO: ObjSERVICIO)
        {
            val tvNumeroServicio = itemView.findViewById(R.id.OrdenServicio) as TextView
            val tvNomCliente = itemView.findViewById(R.id.nomServicio) as TextView
            tvNumeroServicio.text = oObjSERVICIO.numeroOrdenServicio.toString()
            tvNomCliente.text =  oObjSERVICIO.nombreCliente.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorServicioViewHolder {
        val v: View = LayoutInflater.from(parent?.context)
            .inflate(R.layout.servicio,parent,false)
        return AdaptadorServicio.AdaptadorServicioViewHolder(v)
    }
    override fun getItemCount(): Int {
        return listaInterna.size
    }
    override fun onBindViewHolder(holder: AdaptadorServicioViewHolder, position: Int) {
        holder?.itemView.OrdenServicio?.text  = listaInterna.get(position).numeroOrdenServicio.toString()
        holder?.itemView.nomServicio?.text =  listaInterna.get(position).nombreCliente.toString()
        holder?.itemView?.setOnClickListener { clickListener(listaInterna.get(position)) }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tvTitle: TextView = itemView.findViewById(R.id.OrdenServicio)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listaInterna[adapterPosition])
            }
        }
        override fun onClick(v: View) {
            onItemClick?.invoke(listaInterna[adapterPosition])
        }
    }
}