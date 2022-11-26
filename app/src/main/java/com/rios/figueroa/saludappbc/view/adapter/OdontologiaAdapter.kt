package com.rios.figueroa.saludappbc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rios.figueroa.saludappbc.R
import com.rios.figueroa.saludappbc.model.medicoGeneral
import com.rios.figueroa.saludappbc.model.odontologia
import com.squareup.picasso.Picasso

class OdontologiaAdapter(val context: android.content.Context):RecyclerView.Adapter<OdontologiaAdapter.ViewHolder>(){
    private var medicosLista= mutableListOf<odontologia>()

    fun setListData(data:MutableList<odontologia>){
        medicosLista = data
    }
    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {

        val v=LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_especialistas,
                ViewGroup, false)
        return ViewHolder(v)

    }
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView){
        fun bin(medico:odontologia){

            Picasso.with(context).load(medico.imagen).into(itemView.findViewById<ImageView>(R.id.image))
            itemView.findViewById<TextView>(R.id.especialidad).text=medico.especialidad
            itemView.findViewById<TextView>(R.id.nombreProf).text=medico.nombre
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val medico=medicosLista[i]
        viewHolder.bin(medico)
    }

    override fun getItemCount(): Int {
        return if(medicosLista.size>0){
            medicosLista.size
        }else{
            0
        }
    }

}