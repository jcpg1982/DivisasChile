package pe.com.master.machines.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.R
import pe.com.master.machines.design.components.button.CustomButton
import pe.com.master.machines.design.components.dialog.LoadingData
import pe.com.master.machines.design.components.dialog.SingleAlertDialog
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.text.CustomTextInput
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetTwoHundredFifty
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.login.viewmodel.LoginState
import pe.com.master.machines.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    
    val loginUserState by loginViewModel.loginUserState.collectAsStateWithLifecycle()
    val emailUser by loginViewModel.emailUser.collectAsStateWithLifecycle()
    val passwordUser by loginViewModel.passwordUser.collectAsStateWithLifecycle()
    val enabledButton by loginViewModel.enabledButton.collectAsStateWithLifecycle()
    
    var messageError by remember { mutableStateOf("") }
    var messageLoading by remember { mutableStateOf("") }
    
    LaunchedEffect(loginUserState) {
        when (val state = loginUserState) {
            is LoginState.Error -> {
                messageLoading = ""
                messageError = state.messageError
            }
            
            LoginState.First -> {
                messageError = ""
                messageLoading = ""
            }
            
            is LoginState.Loading -> {
                messageError = ""
                messageLoading = state.message
            }
            
            LoginState.Success -> {
                messageError = ""
                messageLoading = ""
                navigateToHome()
            }
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ContentInset),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Spacer(modifier = Modifier.weight(1f))
        
        Image(
            painter = painterResource(R.drawable.loader_divisas_chile),
            contentDescription = "Image Loading",
            modifier = Modifier
                .size(ContentInsetTwoHundredFifty),
            alignment = Alignment.Center
        )
        
        Spacer(modifier = Modifier.height(ContentInset))
        
        CustomTextInput(
            value = emailUser,
            hintText = stringResource(R.string.hint_email),
            leadingIcon = R.drawable.ic_action_email,
            onTextValueChange = {
                loginViewModel.updateEmailUser(it)
            }
        )
        
        CustomTextInput(
            value = passwordUser,
            hintText = stringResource(R.string.hint_password),
            leadingIcon = R.drawable.ic_action_pad_lock,
            keyboardType = KeyboardType.Password,
            onTextValueChange = {
                loginViewModel.updatePasswordUser(it)
            }
        )
        
        Spacer(modifier = Modifier.height(ContentInset))
        
        CustomText(
            modifier = Modifier
                .clickable {
                    navigateToRegister()
                }
                .wrapContentWidth(),
            textColor = MaterialTheme.colorScheme.primary,
            text = stringResource(R.string.text_register),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = DynamicTextSixteen,
        )
        
        Spacer(modifier = Modifier.height(ContentInset))
        
        CustomButton(
            modifier = Modifier.fillMaxWidth(.7f),
            textButton = stringResource(R.string.action_next),
            enabledButton = enabledButton,
            onClickButton = {
                loginViewModel.loginUser()
            }
        )
        
        Spacer(modifier = Modifier.weight(1f))
    }
    
    if (messageError.isNotEmpty()) {
        SingleAlertDialog(
            title = "Atención",
            message = messageError,
            onDismiss = { messageError = "" }
        )
    }
    
    if (messageLoading.isNotEmpty()) LoadingData(messageLoading)
    
}