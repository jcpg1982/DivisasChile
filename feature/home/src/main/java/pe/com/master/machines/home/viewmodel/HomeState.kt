package pe.com.master.machines.home.viewmodel

import pe.com.master.machines.model.reponse.ResponseTimeFrame

sealed interface HomeState {
    
    data object First : HomeState
    data class Loading(val message: String) : HomeState
    data class Error(val messageError: String) : HomeState
    data class Success(val data: ResponseTimeFrame) : HomeState
}