package com.rios.figueroa.saludappbc.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.rios.figueroa.saludappbc.model.odontologia

class RepositoryOdontologia {

    fun getMedicosData(): LiveData<MutableList<odontologia>> {
        val mutabledata= MutableLiveData<MutableList<odontologia>>()
        FirebaseFirestore.getInstance().collection("odontologia").get().addOnSuccessListener {
                result -> val listData= mutableListOf<odontologia>()
            for (document in result){
                val nombre=document.getString("nombre")
                val especialidad=document.getString("especialidad")
                val imagen=document.getString("imagen")
                val medico= odontologia(nombre!!,especialidad!!,imagen!!)
                listData.add(medico)
            }
            mutabledata.value=listData
        }
        return mutabledata
    }
}