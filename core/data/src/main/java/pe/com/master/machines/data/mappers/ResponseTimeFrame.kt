package pe.com.master.machines.data.mappers

import pe.com.master.machines.model.reponse.ResponseTimeFrame
import pe.com.master.machines.network.model.response.ResponseTimeFrameNetwork

fun ResponseTimeFrameNetwork.asModel() = ResponseTimeFrame(
    endDate = this.endDate.orEmpty(),
    privacy = this.privacy.orEmpty(),
    quotes = this.quotes ?: mapOf(),
    source = this.source.orEmpty(),
    startDate = this.startDate.orEmpty(),
    success = this.success ?: false,
    terms = this.terms.orEmpty(),
    timeframe = this.timeframe ?: false,
)