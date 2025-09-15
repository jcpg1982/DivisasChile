package pe.com.master.machines.design.components.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.R
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.DynamicTextTwentyFour

@Composable
fun Toolbar(
    title: String,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ContentInset),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            modifier = Modifier
                .clickable {
                    onClickBack()
                }
                .background(color = Color.Transparent),
            painter = painterResource(R.drawable.ic_action_arrow_back),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(ContentInsetEight))

        CustomText(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            textColor = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            fontSize = DynamicTextTwentyFour
        )
    }
}

@Preview
@Composable
fun PreviewToolbar(modifier: Modifier = Modifier) {
    Toolbar(
        title = "Registrate",
        onClickBack = { }
    )
}