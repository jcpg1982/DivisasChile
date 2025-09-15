package pe.com.master.machines.model.reponse

import java.math.BigDecimal

data class ResponseTimeFrame(
    val endDate: String,
    val privacy: String,
    val quotes: Map<String, Map<String, BigDecimal>>,
    val source: String,
    val startDate: String,
    val success: Boolean,
    val terms: String,
    val timeframe: Boolean
)