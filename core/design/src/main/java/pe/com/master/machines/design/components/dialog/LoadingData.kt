package pe.com.master.machines.design.components.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.com.master.machines.design.R
import pe.com.master.machines.design.theme.ColorWhite
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetFour
import pe.com.master.machines.design.theme.ContentInsetOneHundred
import pe.com.master.machines.design.theme.ContentInsetOneHundredFifty
import pe.com.master.machines.design.theme.DynamicTextSixteen

@Composable
fun LoadingData(
    message: String = "Loading....."
) {
    Dialog(
        onDismissRequest = { }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = ContentInsetFour,
                    modifier = Modifier
                        .size(ContentInsetOneHundredFifty)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(R.drawable.loader_divisas_chile),
                    contentDescription = "Image Loading",
                    modifier = Modifier
                        .size(ContentInsetOneHundred)
                        .align(Alignment.Center),
                    alignment = Alignment.Center
                )
            }
            
            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ContentInset, end = ContentInset, top = ContentInset)
                    .align(Alignment.CenterHorizontally),
                color = ColorWhite,
                fontSize = DynamicTextSixteen,
                textAlign = TextAlign.Center
            )
        }
    }
}