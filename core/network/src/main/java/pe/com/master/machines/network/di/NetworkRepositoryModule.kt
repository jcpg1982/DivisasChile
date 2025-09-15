package pe.com.master.machines.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.network.repository.TimeFrameNetworkRepository
import pe.com.master.machines.network.repositoryImpl.TimeFrameNetworkRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepositoryModule {

    @Binds
    abstract fun provideTimeFrameNetworkRepository(timeFrameNetworkRepositoryImpl: TimeFrameNetworkRepositoryImpl): TimeFrameNetworkRepository

}