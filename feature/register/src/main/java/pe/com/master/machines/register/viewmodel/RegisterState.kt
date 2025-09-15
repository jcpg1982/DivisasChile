package pe.com.master.machines.register.viewmodel

sealed interface RegisterState {

    data object First : RegisterState
    data class Loading(val message: String) : RegisterState
    data class Error(val messageError: String) : RegisterState
    data object Success : RegisterState
}