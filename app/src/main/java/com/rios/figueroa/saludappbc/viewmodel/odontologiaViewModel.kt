package com.rios.figueroa.saludappbc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rios.figueroa.saludappbc.Domain.RepositoryGeneral
import com.rios.figueroa.saludappbc.Domain.RepositoryOdontologia
import com.rios.figueroa.saludappbc.model.medicoGeneral
import com.rios.figueroa.saludappbc.model.odontologia

class odontologiaViewModel: ViewModel() {
    val repositoryOdontologia= RepositoryOdontologia()

    fun fetchOdontologiaData(): LiveData<MutableList<odontologia>> {
        val mutableLiveData= MutableLiveData<MutableList<odontologia>>()
        repositoryOdontologia.getMedicosData().observeForever {
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}