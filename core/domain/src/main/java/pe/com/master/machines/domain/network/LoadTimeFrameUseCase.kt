package pe.com.master.machines.domain.network

import pe.com.master.machines.data.repository.network.TimeFrameDataRepository
import javax.inject.Inject

class LoadTimeFrameUseCase @Inject constructor(
    private var timeFrameDataRepository: TimeFrameDataRepository,
) {

    operator fun invoke(startDate: String, endDate: String, currencies: String) =
        timeFrameDataRepository.getLoadTimeFrame(startDate, endDate, currencies)
}