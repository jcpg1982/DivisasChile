package pe.com.master.machines.design.components.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetTwentyFour
import pe.com.master.machines.design.theme.DynamicTextTwentyFour

@Composable
fun ToolbarHome(
    title: String,
    menuOptions: List<Pair<String, () -> Unit>>,
) {
    
    var menuExpanded by remember { mutableStateOf(false) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = ContentInset),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomText(
            modifier = Modifier.wrapContentWidth(),
            text = title,
            textColor = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            fontSize = DynamicTextTwentyFour
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Box {
            Icon(
                modifier = Modifier
                    .size(ContentInsetTwentyFour)
                    .clickable { menuExpanded = true },
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Abrir menú",
                tint = MaterialTheme.colorScheme.primary
            )
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                menuOptions.forEach { (optionText, onOptionClick) ->
                    DropdownMenuItem(
                        text = { Text(optionText) },
                        onClick = {
                            onOptionClick()
                            menuExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewToolbarHome(modifier: Modifier = Modifier) {
    val sampleMenuOptions = listOf(
        "Opción 1" to { /* Acción para Opción 1 */ },
        "Opción 2" to { /* Acción para Opción 2 */ },
        "Opción 3" to { /* Acción para Opción 3 */ }
    )
    ToolbarHome(
        title = "Registrate",
        menuOptions = sampleMenuOptions
    )
}