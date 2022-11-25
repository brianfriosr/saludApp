package com.rios.figueroa.saludappbc.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rios.figueroa.saludappbc.R

class GeneralAdapter: RecyclerView.Adapter<GeneralAdapter.ViewHolder>(){

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {

        val v= LayoutInflater.from(ViewGroup.context).inflate(
            R.layout.card_view_especialistas,
            ViewGroup, false)
        return ViewHolder(v)

    }
    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        var itemImagen: ImageView
        var itemTitle: TextView
        var itemEspecialidad: TextView

        init {
            itemImagen= ItemView.findViewById(R.id.image)
            itemTitle= ItemView.findViewById(R.id.especialidad)
            itemEspecialidad= ItemView.findViewById(R.id.nombreProf )
        }
    }
    val titles= arrayOf("Dr. Smith Anderson","Dr. Jessica Riley","Dr. Samantha Jung", "Dr. Jonathan Otero")
    val especialidad= arrayOf("Medico","Homeopata","Ginecologo", "Bacteriologo")
    val imagen = arrayOf(R.drawable.smith, R.drawable.riley, R.drawable.jung, R.drawable.otero)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=titles[i]
        viewHolder.itemEspecialidad.text=especialidad[i]
        viewHolder.itemImagen.setImageResource(imagen[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

}