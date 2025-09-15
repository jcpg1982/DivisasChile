package pe.com.master.machines.register.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.design.R
import pe.com.master.machines.design.components.button.CustomButton
import pe.com.master.machines.design.components.dialog.LoadingData
import pe.com.master.machines.design.components.dialog.SingleAlertDialog
import pe.com.master.machines.design.components.text.CustomTextInput
import pe.com.master.machines.design.components.toolbar.Toolbar
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.register.viewmodel.RegisterState
import pe.com.master.machines.register.viewmodel.RegisterUserViewModel

@Composable
fun RegisterUserScreen(
    navigateToBack: () -> Unit,
    navigateToHome: () -> Unit,
    registerUserViewModel: RegisterUserViewModel = hiltViewModel()
) {
    
    val registerUserState by registerUserViewModel.registerUserState.collectAsStateWithLifecycle()
    val user by registerUserViewModel.user.collectAsStateWithLifecycle()
    val repeatPassword by registerUserViewModel.repeatPassword.collectAsStateWithLifecycle()
    val enabledButton by registerUserViewModel.enabledButton.collectAsStateWithLifecycle()
    
    
    var messageError by remember { mutableStateOf("") }
    var messageLoading by remember { mutableStateOf("") }
    
    LaunchedEffect(registerUserState) {
        when (val state = registerUserState) {
            is RegisterState.Error -> {
                messageLoading = ""
                messageError = state.messageError
            }
            
            RegisterState.First -> {
                messageError = ""
                messageLoading = ""
            }
            
            is RegisterState.Loading -> {
                messageError = ""
                messageLoading = state.message
            }
            
            RegisterState.Success -> {
                messageError = ""
                messageLoading = ""
                navigateToHome()
            }
        }
    }
    
    Scaffold(
        topBar = {
            Toolbar(
                title = "Registrate",
                onClickBack = navigateToBack
            )
        }
    ) { paddingValues ->
        
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(ContentInset),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(ContentInset))
            
            CustomTextInput(
                value = user.email,
                hintText = stringResource(R.string.hint_email),
                leadingIcon = R.drawable.ic_action_email,
                onTextValueChange = {
                    registerUserViewModel.updateUser(
                        user.copy(email = it)
                    )
                }
            )
            
            CustomTextInput(
                value = user.firstName,
                hintText = stringResource(R.string.hint_first_name),
                leadingIcon = R.drawable.ic_action_person,
                keyboardType = KeyboardType.Text,
                onTextValueChange = {
                    registerUserViewModel.updateUser(
                        user.copy(firstName = it)
                    )
                }
            )
            
            CustomTextInput(
                value = user.lastName,
                hintText = stringResource(R.string.hint_last_name),
                leadingIcon = R.drawable.ic_action_person,
                keyboardType = KeyboardType.Text,
                onTextValueChange = {
                    registerUserViewModel.updateUser(
                        user.copy(lastName = it)
                    )
                }
            )
            
            CustomTextInput(
                value = user.password,
                hintText = stringResource(R.string.hint_password),
                leadingIcon = R.drawable.ic_action_pad_lock,
                keyboardType = KeyboardType.Password,
                onTextValueChange = {
                    registerUserViewModel.updateUser(
                        user.copy(password = it)
                    )
                }
            )
            
            CustomTextInput(
                value = repeatPassword,
                hintText = stringResource(R.string.hint_repeat_password),
                leadingIcon = R.drawable.ic_action_pad_lock,
                keyboardType = KeyboardType.Password,
                onTextValueChange = {
                    registerUserViewModel.updateRepeatPassword(it)
                }
            )
            
            Spacer(modifier = Modifier.height(ContentInset))
            
            CustomButton(
                modifier = Modifier.fillMaxWidth(.7f),
                textButton = stringResource(R.string.action_register),
                enabledButton = enabledButton,
                onClickButton = {
                    registerUserViewModel.registerUser()
                }
            )
            
        }
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