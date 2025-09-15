package pe.com.master.machines.data.repository.network

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.reponse.ResponseTimeFrame

interface TimeFrameDataRepository {

    fun getLoadTimeFrame(
        startDate: String, endDate: String, currencies: String
    ): Flow<Resource<ResponseTimeFrame>>
}