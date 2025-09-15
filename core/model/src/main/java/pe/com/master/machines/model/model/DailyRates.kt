package pe.com.master.machines.model.model

import java.math.BigDecimal

data class DailyRates(
    val date: String,
    val usdToClp: BigDecimal,
    val usdToEur: BigDecimal,
    val eurToClp: BigDecimal,
    val eurToUsd: BigDecimal,
    val clpToUsd: BigDecimal,
    val clpToEur: BigDecimal
)
