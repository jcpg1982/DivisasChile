package pe.com.master.machines.design.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pe.com.master.machines.design.R

val robotoRegular
    @Composable
    get() = Font(R.font.roboto_regular, FontWeight.Normal)

val Typography
    @Composable
    get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = FontFamily(robotoRegular),
            fontWeight = FontWeight.Normal,
            fontSize = DynamicTextSixteen,
            lineHeight = DynamicTextSixteen * 1.3,
            letterSpacing = DynamicPutFive
        )
    )