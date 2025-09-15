package pe.com.master.machines.model.reponse

import pe.com.master.machines.model.model.DailyRates

data class ResponseTimeFrame(
    val endDate: String,
    val privacy: String,
    val quotes: List<DailyRates>,
    val source: String,
    val startDate: String,
    val success: Boolean,
    val terms: String,
    val timeframe: Boolean
)