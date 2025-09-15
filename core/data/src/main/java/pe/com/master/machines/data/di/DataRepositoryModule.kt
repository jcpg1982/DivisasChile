package pe.com.master.machines.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.data.repository.database.UserDataRepository
import pe.com.master.machines.data.repository.network.TimeFrameDataRepository
import pe.com.master.machines.data.repositoryImpl.database.UserDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.network.TimeFrameDataRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun provideUserDataRepository(userDataRepositoryImpl: UserDataRepositoryImpl): UserDataRepository

    @Binds
    abstract fun provideTimeFrameDataRepository(timeFrameDataRepositoryImpl: TimeFrameDataRepositoryImpl): TimeFrameDataRepository

}