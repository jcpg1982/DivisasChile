package pe.com.master.machines.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.utils.messageError
import pe.com.master.machines.design.utils.DateUtils
import pe.com.master.machines.design.utils.DateUtils.calendarLongToString
import pe.com.master.machines.domain.network.LoadTimeFrameUseCase
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadTimeFrameUseCase: LoadTimeFrameUseCase,
) : ViewModel() {
    
    private var _homeUserState = MutableStateFlow<HomeState>(HomeState.First)
    val homeUserState get() = _homeUserState.asStateFlow()
    
    var dateInitStart: String? = null
    var dateInitEnd: String? = null
    
    init {
        val calendar = DateUtils.getCurrentCalendar
        dateInitEnd = DateUtils.getCurrentTime.time.calendarLongToString(DateUtils.YYYY_MM_DD)
        
        calendar.add(Calendar.DAY_OF_MONTH, -15)
        dateInitStart = calendar.time.time.calendarLongToString(DateUtils.YYYY_MM_DD)
        
        if (!dateInitStart.isNullOrBlank() && !dateInitEnd.isNullOrBlank()) {
            loginDataTimeFrame(dateInitStart.orEmpty(), dateInitEnd.orEmpty())
        }
    }
    
    fun filterByDateRange(startDate: String?, endDate: String?) {
        dateInitStart = startDate
        dateInitEnd = endDate
        dateInitStart?.let { start ->
            dateInitEnd?.let { end ->
                loginDataTimeFrame(start, end)
            }
        }
    }
    
    fun loginDataTimeFrame(startDate: String, endDate: String) {
        viewModelScope.launch {
            loadTimeFrameUseCase.invoke(startDate, endDate)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _homeUserState.update { HomeState.Loading("Cargando datos") }
                }
                .catch { e ->
                    _homeUserState.update { HomeState.Error(e.message ?: "Error inesperado") }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> _homeUserState.update { HomeState.Error(res.messageError) }
                        
                        is Resource.Success -> _homeUserState.update { HomeState.Success(res.data) }
                    }
                }
        }
    }
    
}