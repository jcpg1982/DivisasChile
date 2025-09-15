package pe.com.master.machines.network.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class ResponseTimeFrameNetwork(
    @SerializedName("end_date")
    val endDate: String? = null,
    @SerializedName("privacy")
    val privacy: String? = null,
    @SerializedName("quotes")
    val quotes: Map<String, Map<String, BigDecimal>>? = null,
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("start_date")
    val startDate: String? = null,
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("terms")
    val terms: String? = null,
    @SerializedName("timeframe")
    val timeframe: Boolean? = null
)