package pe.com.master.machines.network.api

import pe.com.master.machines.network.model.response.ResponseTimeFrameNetwork
import pe.com.master.machines.network.utils.Utils.Endpoints.TIME_FRAME
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(TIME_FRAME)
    suspend fun getLoadTimeFrame(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("currencies") currencies: String,
        @Query("access_key") accessKey: String
    ): ResponseTimeFrameNetwork
}