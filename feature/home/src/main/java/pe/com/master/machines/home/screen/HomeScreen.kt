package pe.com.master.machines.home.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import pe.com.master.machines.design.components.dialog.DateRangePickerModal
import pe.com.master.machines.design.components.dialog.LoadingData
import pe.com.master.machines.design.components.dialog.SingleAlertDialog
import pe.com.master.machines.design.components.dialog.TwoAlertDialog
import pe.com.master.machines.design.components.text.CustomText
import pe.com.master.machines.design.components.toolbar.ToolbarHome
import pe.com.master.machines.design.theme.ContentInset
import pe.com.master.machines.design.theme.ContentInsetThreeHundredFifty
import pe.com.master.machines.home.viewmodel.HomeState
import pe.com.master.machines.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    
    val homeUserState by homeViewModel.homeUserState.collectAsStateWithLifecycle()
    
    var openCalendar by remember { mutableStateOf(false) }
    var messageError by remember { mutableStateOf("") }
    
    val menuOptions = listOf(
        "Calendario" to { openCalendar = !openCalendar },
        "Cerrar sesion" to { messageError = "Desea salir de la aplicacion?" },
    )
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarHome(
                title = "Home",
                menuOptions = menuOptions
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val state = homeUserState) {
                is HomeState.Error -> {
                    messageError = state.messageError
                }
                
                HomeState.First -> {
                    messageError = ""
                }
                
                is HomeState.Loading -> {
                    messageError = ""
                    LoadingData(state.message)
                }
                
                is HomeState.Success -> {
                    Log.d("HomeScreen", "Success: ${state.data}")
                    messageError = ""
                    if (state.data.quotes.isNotEmpty()) {
                        AndroidView(
                            factory = { ctx ->
                                LineChart(ctx).apply {
                                    description.isEnabled = false
                                    axisRight.isEnabled = false
                                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                                    legend.isEnabled = true
                                    xAxis.granularity = 1f
                                    xAxis.labelRotationAngle = 90f
                                    xAxis.setLabelCount(10, true)
                                    xAxis.setAvoidFirstLastClipping(true)
                                }
                            },
                            update = { chart ->
                                val history = state.data.quotes
                                val entriesUsd = history.mapIndexed { i, point ->
                                    Entry(i.toFloat(), point.usdToClp.toFloat())
                                }
                                val entriesEur = history.mapIndexed { i, point ->
                                    Entry(i.toFloat(), point.eurToClp.toFloat())
                                }
                                
                                val dsUsd = LineDataSet(entriesUsd, "USD/CLP").apply {
                                    color = android.graphics.Color.BLUE
                                    setCircleColor(android.graphics.Color.BLUE)
                                    lineWidth = 2f
                                    setDrawValues(false)
                                }
                                val dsEur = LineDataSet(entriesEur, "EUR/CLP").apply {
                                    color = android.graphics.Color.RED
                                    setCircleColor(android.graphics.Color.RED)
                                    lineWidth = 2f
                                    setDrawValues(false)
                                }
                                
                                chart.data = LineData(dsUsd, dsEur)
                                chart.xAxis.valueFormatter = object : ValueFormatter() {
                                    override fun getFormattedValue(value: Float): String {
                                        val index = value.toInt()
                                        return history.getOrNull(index)?.date ?: ""
                                    }
                                }
                                
                                chart.invalidate()
                            },
                            modifier = Modifier
                                .padding(horizontal = ContentInset)
                                .fillMaxWidth()
                                .height(ContentInsetThreeHundredFifty)
                        )
                    } else {
                        CustomText("No hay datos para mostrar")
                    }
                }
            }
        }
    }
    
    if (messageError.isNotEmpty()) {
        TwoAlertDialog(
            title = "Salir de la aplicacion",
            message = messageError,
            onAccept = {
                messageError = ""
                navigateToLogin()
            },
            onDismiss = { messageError = "" }
        )
    }
    
    if (openCalendar) {
        DateRangePickerModal(
            initialStartDate = homeViewModel.dateInitStart,
            initialEndDate = homeViewModel.dateInitEnd,
            colorPrimary = MaterialTheme.colorScheme.primary,
            onDateRangeSelected = {
                homeViewModel.filterByDateRange(it.first, it.second)
                openCalendar = false
            },
            onDismiss = {
                openCalendar = false
            }
        )
    }
    
    if (messageError.isNotEmpty()) {
        SingleAlertDialog(
            title = "Atención",
            message = messageError,
            onDismiss = { messageError = "" }
        )
    }
    
}