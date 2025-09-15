package pe.com.master.machines.design.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    
    const val DD_d_MMMM_dl_YYYY = "dd 'de' MMMM 'del' yyyy"
    const val DD_d_MMMM_d_YYYY = "dd 'de' MMMM 'de' yyyy"
    const val DD_d_MMM_dl_YYYY = "dd 'de' MMM 'del' yyyy"
    const val DD_d_MMM_d_YYYY = "dd 'de' MMM 'de' yyyy"
    const val EEE_DD_MMM_YYYY = "EEE., dd MMM  yyyy"
    const val HH_MM = "HH:mm"
    const val HH_MM_SS = "$HH_MM:ss"
    const val HH_MM_SS_SSS = "$HH_MM_SS.SSS"
    const val HH_MM_SS_SS = "$HH_MM_SS.SS"
    const val HH_MM_SS_SSS_Z = "$HH_MM_SS_SSS'Z'"
    const val HH_MM_SS_SS_Z = "$HH_MM_SS_SS'Z'"
    const val HH_MM_SS_Z = "$HH_MM_SS'Z'"
    const val HH_MM_A = "h:mm a"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val DD_MM = "dd/MM"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val DD_MM_YYYY_HH_MM_SS = "$DD_MM_YYYY $HH_MM_SS"
    const val DD_MM_YYYY_HH_MM = "$DD_MM_YYYY $HH_MM"
    const val DD_MM_YYYY_HH_MM_SS_SSS = "$DD_MM_YYYY $HH_MM_SS_SSS"
    const val DD_MM_YYYY_HH_MM_SS_SSS_Z = "$DD_MM_YYYY_HH_MM_SS_SSS Z"
    const val DD_MM_YYYY_HH_MM_SS_Z = "$DD_MM_YYYY $HH_MM_SS Z"
    const val DD_MM_YYYY_HH_MM_Z = "$DD_MM_YYYY $HH_MM Z"
    const val DD_MM_YYYY_HH_Z = "$DD_MM_YYYY HH Z"
    const val DD_MM_YYYY_Z = "$DD_MM_YYYY Z"
    const val DD_MM_Z = "$DD_MM Z"
    const val DD_MM_HH_MM_SS = "$DD_MM $HH_MM_SS"
    const val DD_MM_HH_MM = "$DD_MM $HH_MM"
    const val DD_MM_HH = "$DD_MM HH"
    const val DD_MM_HH_Z = "$DD_MM HH Z"
    const val DD_MM_HH_MM_Z = "$DD_MM $HH_MM Z"
    const val DD_MM_HH_MM_SS_Z = "$DD_MM $HH_MM_SS Z"
    const val DD_MM_HH_MM_SS_SSS_Z = "$DD_MM $HH_MM_SS.SSS Z"
    const val DD_MM_HH_MM_SS_SSS = "$DD_MM $HH_MM_SS.SSS"
    const val DD_MM_YYYY_HH_MM_SS_A = "$DD_MM_YYYY h:mm:ss a"
    const val DD_MM_YYYY_HH_MM_A = "$DD_MM_YYYY $HH_MM_A"
    const val DD_d_MMMM_d_YYYY_HH_MM_A = "$DD_d_MMMM_d_YYYY $HH_MM_A"
    const val DD_d_MMMM_d_YYYY_HH_MM = "$DD_d_MMMM_d_YYYY $HH_MM"
    const val DD_d_MMM_d_YYYY_HH_MM_A = "$DD_d_MMM_d_YYYY $HH_MM_A"
    const val DD_d_MMM_d_YYYY_HH_MM = "$DD_d_MMM_d_YYYY $HH_MM"
    const val YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "$YYYY_MM_DD'T'$HH_MM_SS_SSS_Z"
    const val YYYY_MM_DD_T_HH_MM_SS_SS_Z = "$YYYY_MM_DD'T'$HH_MM_SS_SS_Z"
    const val YYYY_MM_DD_T_HH_MM_SS_Z = "$YYYY_MM_DD'T'$HH_MM_SS_Z"
    const val YYYY_MM_DD_T_HH_MM_SS = "$YYYY_MM_DD'T'$HH_MM_SS"
    
    val getCurrentCalendar
        get() = Calendar.getInstance(Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }
    
    val getCurrentTime get() = getCurrentCalendar.time
    val getCurrentDateMillis get() = getCurrentCalendar.timeInMillis
    
    val String.simpleDateFormat
        get() = SimpleDateFormat(this, Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }
    
    fun String.toDate(format: String): Date? {
        return try {
            val dateFormat = format.simpleDateFormat
            dateFormat.parse(this)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    fun Long?.calendarLongToString(format: String): String =
        if (this != null) SimpleDateFormat(format, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.format(Date(this))
        else ""
}