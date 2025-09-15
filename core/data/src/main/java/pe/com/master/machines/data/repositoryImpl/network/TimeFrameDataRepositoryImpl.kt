package pe.com.master.machines.data.repositoryImpl.network

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.network.TimeFrameDataRepository
import pe.com.master.machines.network.repository.TimeFrameNetworkRepository
import javax.inject.Inject

class TimeFrameDataRepositoryImpl @Inject constructor(private val timeFrameNetworkRepository: TimeFrameNetworkRepository) :
    TimeFrameDataRepository {

    override fun getLoadTimeFrame(startDate: String, endDate: String, currencies: String) =
        timeFrameNetworkRepository.getLoadTimeFrame(startDate, endDate, currencies).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data.asModel())

                is Resource.Error -> Resource.Error(res.error)
            }
        }.catch {
            emit(Resource.Error(it.toErrorType()))
        }
}