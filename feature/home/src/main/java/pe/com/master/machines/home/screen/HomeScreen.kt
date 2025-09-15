package pe.com.master.machines.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.com.master.machines.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val emailUser by homeViewModel.emailUser.collectAsStateWithLifecycle()
    val passwordUser by homeViewModel.passwordUser.collectAsStateWithLifecycle()
    val enabledButton by homeViewModel.enabledButton.collectAsStateWithLifecycle()

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(ContentInset),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        CustomTextInput(
            value = emailUser,
            hintText = stringResource(R.string.hint_email),
            leadingIcon = R.drawable.ic_action_email,
            onTextValueChange = {
                homeViewModel.updateEmailUser(it)
            }
        )

        CustomTextInput(
            value = passwordUser,
            hintText = stringResource(R.string.hint_password),
            leadingIcon = R.drawable.ic_action_pad_lock,
            keyboardType = KeyboardType.Password,
            onTextValueChange = {
                homeViewModel.updatePasswordUser(it)
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
                homeViewModel.loginUser()
            }
        )

        Spacer(modifier = Modifier.weight(1f))
    }*/

}