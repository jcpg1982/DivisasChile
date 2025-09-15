package pe.com.master.machines.design.components.text

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.R
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.ConversorDivisaTheme
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwelve
import pe.com.master.machines.design.theme.robotoRegular

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = DynamicTextFourteen,
    minLines: Int = 1,
    maxLines: Int = 1,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = textColor,
        textAlign = textAlign,
        overflow = overflow,
        minLines = minLines,
        maxLines = maxLines,
        lineHeight = fontSize * 1.5f,
        fontFamily = FontFamily(robotoRegular)
    )
}

@Composable
fun CustomText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = DynamicTextFourteen,
    minLines: Int = 1,
    maxLines: Int = 1,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = textColor,
        textAlign = textAlign,
        overflow = overflow,
        minLines = minLines,
        maxLines = maxLines,
        lineHeight = fontSize * 1.5f,
        fontFamily = FontFamily(robotoRegular)
    )
}

@Composable
fun CustomText(
    value: TextFieldValue,
    hintText: String = "",
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    isViewCount: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    minLines: Int = 1,
    maxLines: Int = 5,
    maxCharacter: Int = 50,
    modifier: Modifier = Modifier,
    trailingIcon: Int? = null,
    trailingIconColor: Color = MaterialTheme.colorScheme.primary,
    roundedShape: Dp = ContentInsetFour,
    messageError: String = "",
    onTextValueChange: (String) -> Unit,
    onClickTextView: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                val result = it.text
                onTextValueChange(result)
            },
            modifier = modifier,
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
            singleLine = maxLines == 1,
            minLines = minLines,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = RoundedCornerShape(roundedShape),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = trailingIconColor,
                unfocusedIndicatorColor = trailingIconColor.copy(alpha = .5f),
                cursorColor = trailingIconColor
            )
        )
        if (isViewCount) {
            CustomText(
                text = "${value.text.length}/$maxCharacter",
                fontSize = DynamicTextTwelve,
                maxLines = 1,
                textColor = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight(400)
            )
        }
    }

    /*LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                onClickTextView()
            }
        }
    }*/
}

@Preview
@Composable
fun PreviewCustomText() {
    Column {
        ConversorDivisaTheme(
            darkTheme = true
        ) {
            CustomText(text = "Jorge luna")
        }
        ConversorDivisaTheme(
            darkTheme = false
        ) {
            CustomText(text = "Jorge luna")
        }
    }
}