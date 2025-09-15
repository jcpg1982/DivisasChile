package pe.com.master.machines.sqlite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.sqlite.repository.UserEntityDatabaseRepository
import pe.com.master.machines.sqlite.repositoryImpl.UserEntityDatabaseRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class SqliteModule {

    @Binds
    abstract fun provideUserEntityDatabaseRepository(userEntityDatabaseRepositoryImpl: UserEntityDatabaseRepositoryImpl): UserEntityDatabaseRepository

}