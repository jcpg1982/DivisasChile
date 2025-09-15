package pe.com.master.machines.design.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetOne
import pe.com.master.machines.design.theme.DynamicTextTwelve

@Composable
fun CustomButton(
    textButton: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: TextUnit = DynamicTextTwelve,
    enabledButton: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    rounded: Dp = ContentInset,
    onClickButton: () -> Unit
) {
    Button(
        onClick = { onClickButton() },
        modifier = modifier,
        enabled = enabledButton,
        shape = RoundedCornerShape(rounded),
        border = BorderStroke(ContentInsetOne, borderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f),
            disabledContentColor = textColor.copy(alpha = 0.5f)
        )
    ) {
        CustomText(
            modifier = Modifier.padding(vertical = ContentInsetEight),
            text = textButton,
            maxLines = 1,
            fontSize = fontSize,
            textColor = textColor
        )
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(
        textButton = "Iniciar sesion",
        onClickButton = {}
    )
}