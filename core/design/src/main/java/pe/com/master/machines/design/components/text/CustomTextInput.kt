package pe.com.master.machines.design.components.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import pe.com.master.machines.design.R
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.utils.Constants.Regex.MIXTO

@Composable
fun CustomTextInput(
    value: String,
    modifier: Modifier = Modifier,
    hintText: String = "",
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    isViewCount: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    minLines: Int = 1,
    maxLines: Int = 5,
    maxCharacter: Int = 50,
    regex: Regex = MIXTO,
    leadingIcon: Int? = null,
    leadingIconColor: Color = MaterialTheme.colorScheme.primary,
    trailingIcon: Int? = null,
    trailingIconColor: Color = MaterialTheme.colorScheme.primary,
    roundedShape: Dp = ContentInsetFour,
    messageError: String = "",
    onTextValueChange: (String) -> Unit = {},
    onClickTextView: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val visualTransformation = if (keyboardType == KeyboardType.Password) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                val result = when {
                    it.isBlank() -> {
                        it
                    }
                    
                    it.length <= maxCharacter && it.matches(regex) -> {
                        it
                    }
                    
                    else -> {
                        value
                    }
                }
                onTextValueChange(result)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isEnabled,
            readOnly = isReadOnly,
            label = {
                CustomText(
                    modifier = Modifier.wrapContentWidth(),
                    text = hintText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = DynamicTextSixteen,
                )
            },
            supportingText = {
                if (messageError.isNotBlank()) {
                    CustomText(
                        modifier = Modifier.fillMaxWidth(),
                        text = messageError,
                        textColor = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.End
                    )
                }
            },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "",
                        tint = leadingIconColor
                    )
                }
            },
            trailingIcon = {
                if (messageError.isNotBlank()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_action_warning),
                        contentDescription = "error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                trailingIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "",
                        tint = trailingIconColor
                    )
                }
            },
            isError = messageError.isNotBlank(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            singleLine = maxLines == 1,
            minLines = minLines,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = RoundedCornerShape(roundedShape),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = trailingIconColor,
                unfocusedIndicatorColor = trailingIconColor.copy(alpha = .5f),
                cursorColor = trailingIconColor
            )
        )
        if (isViewCount) {
            CustomText(
                text = "${value.length}/$maxCharacter",
                fontSize = DynamicTextTwelve,
                maxLines = 1,
                textColor = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight(400)
            )
        }
    }
    
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClickTextView()
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomTextInput() {
    CustomTextInput(
        value = "es una prueba",
        hintText = "prueba",
        keyboardType = KeyboardType.Password,
        leadingIcon = R.drawable.ic_action_email,
        leadingIconColor = MaterialTheme.colorScheme.primary
    )
}