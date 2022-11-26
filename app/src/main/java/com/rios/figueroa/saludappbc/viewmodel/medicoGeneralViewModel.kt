package com.rios.figueroa.saludappbc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rios.figueroa.saludappbc.Domain.RepositoryGeneral
import com.rios.figueroa.saludappbc.model.medicoGeneral

class medicoGeneralViewModel: ViewModel() {
    val repositoryGeneral=RepositoryGeneral()

    fun fetchMedicoGeneralData():LiveData<MutableList<medicoGeneral>>{
        val mutableLiveData=MutableLiveData<MutableList<medicoGeneral>>()
        repositoryGeneral.getMedicosData().observeForever {
            mutableLiveData.value=it
        }
        return mutableLiveData
    }
}