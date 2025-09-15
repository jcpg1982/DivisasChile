package pe.com.master.machines.design.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import pe.com.master.machines.design.R
import pe.com.master.machines.design.components.button.CustomButton
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetEight
import pe.com.master.machines.design.theme.ContentInsetFive
import pe.com.master.machines.design.theme.ContentInsetTwenty
import pe.com.master.machines.design.theme.DynamicTextSixteen
import pe.com.master.machines.design.theme.DynamicTextTwenty

@Composable
fun TwoAlertDialog(
    title: String,
    message: String,
    onAccept: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        
        Surface(
            shape = RoundedCornerShape(ContentInsetTwenty),
            tonalElevation = ContentInsetFive
        ) {
            Column(
                modifier = Modifier
                    .padding(ContentInset)
                    .fillMaxWidth(.7f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                
                CustomText(
                    modifier = Modifier.wrapContentWidth(),
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontSize = DynamicTextTwenty,
                )
                
                Spacer(Modifier.height(ContentInsetEight))
                
                CustomText(
                    modifier = Modifier.wrapContentWidth(),
                    text = message,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 10,
                    fontSize = DynamicTextSixteen,
                )
                
                Spacer(Modifier.height(ContentInsetEight))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    
                    CustomButton(
                        modifier = Modifier.weight(1f),
                        textButton = stringResource(R.string.action_accept),
                        onClickButton = {
                            onAccept()
                        }
                    )
                    Spacer(Modifier.width(ContentInsetEight))
                    
                    CustomButton(
                        modifier = Modifier.weight(1f),
                        textButton = stringResource(R.string.action_cancel),
                        onClickButton = {
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}