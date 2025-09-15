package pe.com.master.machines.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.components.dialog.TwoAlertDialog
import pe.com.master.machines.design.components.toolbar.ToolbarHome
import pe.com.master.machines.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    
    val emailUser by homeViewModel.emailUser.collectAsStateWithLifecycle()
    val passwordUser by homeViewModel.passwordUser.collectAsStateWithLifecycle()
    val enabledButton by homeViewModel.enabledButton.collectAsStateWithLifecycle()
    
    var messageError by remember { mutableStateOf("") }
    
    val menuOptions = listOf(
        "Calendario" to { },
        "Cerrar sesion" to { messageError = "Desea salir de la aplicacion?" },
    )
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarHome(
                title = "Home",
                menuOptions = menuOptions
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
        
        }
    }
    
    if (messageError.isNotEmpty()) {
        TwoAlertDialog(
            title = "Salir de la aplicacion",
            message = messageError,
            onAccept = {
                messageError = ""
                navigateToLogin()
            },
            onDismiss = { messageError = "" }
        )
    }
    
}