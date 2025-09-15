package pe.com.master.machines.design.components.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import pe.com.master.machines.design.R
import pe.com.master.machines.design.components.button.CustomButton
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetThirtySix
import pe.com.master.machines.design.theme.DynamicTextFourteen
import pe.com.master.machines.design.utils.DateUtils.DD_MM_YYYY
import pe.com.master.machines.design.utils.DateUtils.YYYY_MM_DD
import pe.com.master.machines.design.utils.DateUtils.calendarLongToString
import pe.com.master.machines.design.utils.DateUtils.getCurrentDateMillis
import pe.com.master.machines.design.utils.DateUtils.toDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    colorPrimary: Color,
    modifier: Modifier = Modifier,
    initialStartDate: String? = null,
    initialEndDate: String? = null,
    onDateRangeSelected: (Pair<String?, String?>) -> Unit,
    onDismiss: () -> Unit
) {
    val selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis <= getCurrentDateMillis
        }
    }
    var starDay by remember { mutableStateOf(initialStartDate) }
    var endDay by remember { mutableStateOf(initialEndDate) }
    
    val dateRangePickerState = rememberDateRangePickerState(
        selectableDates = selectableDates,
        initialSelectedStartDateMillis = initialStartDate?.toDate(YYYY_MM_DD)?.time,
        initialSelectedEndDateMillis = initialEndDate?.toDate(YYYY_MM_DD)?.time
    )
    
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            CustomButton(
                modifier = Modifier
                    .padding(horizontal = ContentInsetThirtySix),
                enabledButton = true,
                textButton = "Aceptar",
                backgroundColor = colorPrimary,
                onClickButton = {
                    val starDayEnd = starDay
                    val endDayEnd = if (endDay.isNullOrBlank()) starDay else endDay
                    onDateRangeSelected(
                        Pair(starDayEnd, endDayEnd)
                    )
                    onDismiss()
                }
            )
        },
        modifier = modifier,
        dismissButton = {
            CustomButton(
                modifier = Modifier
                    .padding(horizontal = ContentInsetThirtySix),
                enabledButton = true,
                textButton = stringResource(R.string.action_cancel),
                backgroundColor = colorPrimary.copy(alpha = .6f),
                onClickButton = {
                    onDateRangeSelected(Pair(null, null))
                    onDismiss()
                }
            )
        },
        shape = RoundedCornerShape(ContentInset),
        content = {
            DateRangePicker(
                state = dateRangePickerState,
                title = {
                    CustomText(
                        modifier = Modifier.padding(all = ContentInset),
                        text = "Seleccione su rango de fecha",
                        fontSize = DynamicTextFourteen
                    )
                },
                headline = {
                    val selectedStartDate = dateRangePickerState.selectedStartDateMillis
                    val selectedEndDate = dateRangePickerState.selectedEndDateMillis
                    starDay = selectedStartDate.calendarLongToString(YYYY_MM_DD)
                    endDay = selectedEndDate.calendarLongToString(YYYY_MM_DD)
                    val dateSelected = when {
                        starDay.isNullOrBlank() && endDay.isNullOrBlank() -> "Fecha seleccionada"
                        starDay?.isNotBlank() == true && endDay.isNullOrBlank() -> "$starDay - $starDay"
                        else -> "$starDay - $endDay"
                    }
                    
                    CustomText(
                        modifier = Modifier.padding(all = ContentInset),
                        text = dateSelected,
                        fontSize = DynamicTextFourteen,
                        textAlign = TextAlign.Start
                    )
                },
                showModeToggle = true,
                colors = DatePickerDefaults.colors(
                    todayContentColor = colorPrimary,
                    todayDateBorderColor = colorPrimary,
                    dayInSelectionRangeContainerColor = colorPrimary.copy(alpha = .3f),
                    selectedDayContainerColor = colorPrimary,
                )
            )
        },
    )
}

@Preview
@Composable
fun PreviewDateRangePickerModal() {
    var dateInitStart: String? = null
    var dateInitEnd: String? = null
    DateRangePickerModal(
        initialStartDate = dateInitStart,
        initialEndDate = dateInitEnd,
        colorPrimary = Color.Red,
        modifier = Modifier.wrapContentSize(),
        onDateRangeSelected = {
            dateInitStart = it.first
            dateInitEnd = it.second
        },
        onDismiss = { }
    )
}