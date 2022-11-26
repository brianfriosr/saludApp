package com.rios.figueroa.saludappbc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rios.figueroa.saludappbc.Domain.RepositoryEspecialidades
import com.rios.figueroa.saludappbc.Domain.RepositoryGeneral
import com.rios.figueroa.saludappbc.model.especialista

class especialidadesViewModel:ViewModel() {
    val repositoryEspecialistas= RepositoryEspecialidades()

    fun fetchEspecialistaData(): LiveData<MutableList<especialista>>{
        val mutableLiveData= MutableLiveData<MutableList<especialista>>()
        repositoryEspecialistas.getMedicosData().observeForever {
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}