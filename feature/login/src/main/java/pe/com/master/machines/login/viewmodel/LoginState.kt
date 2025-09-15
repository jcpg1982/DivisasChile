package pe.com.master.machines.login.viewmodel

sealed interface LoginState {

    data object First : LoginState
    data class Loading(val message: String) : LoginState
    data class Error(val messageError: String) : LoginState
    data object Success : LoginState
}