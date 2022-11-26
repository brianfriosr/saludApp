package com.rios.figueroa.saludappbc.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rios.figueroa.saludappbc.model.medicoGeneral

class RepositoryGeneral {

    fun getMedicosData(): LiveData<MutableList<medicoGeneral>> {
        val mutabledata= MutableLiveData<MutableList<medicoGeneral>>()
        FirebaseFirestore.getInstance().collection("medicoGeneral").get().addOnSuccessListener {
                result -> val listData= mutableListOf<medicoGeneral>()
            for (document in result){
                val nombre=document.getString("nombre")
                val especialidad=document.getString("especialidad")
                val imagen=document.getString("imagen")
                val medico= medicoGeneral(nombre!!,especialidad!!,imagen!!)
                listData.add(medico)
            }
           mutabledata.value=listData
        }
        return mutabledata
    }
}