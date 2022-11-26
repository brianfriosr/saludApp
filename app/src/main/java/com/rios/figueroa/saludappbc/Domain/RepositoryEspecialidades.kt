package com.rios.figueroa.saludappbc.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rios.figueroa.saludappbc.model.especialista

class RepositoryEspecialidades {

    fun getMedicosData(): LiveData<MutableList<especialista>> {
        val mutabledata= MutableLiveData<MutableList<especialista>>()
        FirebaseFirestore.getInstance().collection("especialista").get().addOnSuccessListener {
                result -> val listData= mutableListOf<especialista>()
            for (document in result){
                val nombre=document.getString("nombre")
                val especialidad=document.getString("especialidad")
                val imagen=document.getString("imagen")
                val medico= especialista(nombre!!,especialidad!!,imagen!!)
                listData.add(medico)
            }
            mutabledata.value=listData
        }
        return mutabledata
    }
}