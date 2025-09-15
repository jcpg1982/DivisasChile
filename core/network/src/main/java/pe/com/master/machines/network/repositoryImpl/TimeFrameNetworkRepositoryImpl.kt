package pe.com.master.machines.network.repositoryImpl

import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.network.api.ApiService
import pe.com.master.machines.network.repository.TimeFrameNetworkRepository
import pe.com.master.machines.network.utils.Utils.API_KEY
import javax.inject.Inject

class TimeFrameNetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    TimeFrameNetworkRepository {

    override fun getLoadTimeFrame(
        startDate: String, endDate: String, currencies: String
    ) = flow {
        val response = apiService.getLoadTimeFrame(startDate, endDate, currencies, API_KEY)
        emit(Resource.Success(response))
    }
}