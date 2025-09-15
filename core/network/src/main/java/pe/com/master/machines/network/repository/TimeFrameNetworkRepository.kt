package pe.com.master.machines.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.model.response.ResponseTimeFrameNetwork

interface TimeFrameNetworkRepository {

    fun getLoadTimeFrame(
        startDate: String, endDate: String, currencies: String
    ): Flow<Resource<ResponseTimeFrameNetwork>>
}