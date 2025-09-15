package pe.com.master.machines.data.repositoryImpl.database

import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.response.toErrorType
import pe.com.master.machines.data.mappers.asModel
import pe.com.master.machines.data.repository.database.UserDataRepository
import pe.com.master.machines.model.model.User
import pe.com.master.machines.sqlite.repository.UserEntityDatabaseRepository

class UserDataRepositoryImpl @Inject constructor(private val userEntityDatabaseRepository: UserEntityDatabaseRepository) :
    UserDataRepository {

    override fun insertUser(user: User) =
        userEntityDatabaseRepository.insertUser(user.asModel()).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data)

                is Resource.Error -> Resource.Error(res.error)
            }
        }.catch {
            emit(Resource.Error(it.toErrorType()))
        }

    override fun getAllUsers() =
        userEntityDatabaseRepository.getAllUsers().map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data.asModel())

                is Resource.Error -> Resource.Error(res.error)
            }
        }.catch {
            emit(Resource.Error(it.toErrorType()))
        }

    override fun isEmailTaken(email: String) =
        userEntityDatabaseRepository.isEmailTaken(email).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data)

                is Resource.Error -> Resource.Error(res.error)
            }
        }.catch {
            emit(Resource.Error(it.toErrorType()))
        }

    override fun login(
        email: String, password: String
    ) = userEntityDatabaseRepository.login(email, password).map { res ->
        when (res) {
            is Resource.Success -> Resource.Success(res.data?.asModel())

            is Resource.Error -> Resource.Error(res.error)
        }
    }.catch {
        emit(Resource.Error(it.toErrorType()))
    }

    override fun getUserById(id: Int) =
        userEntityDatabaseRepository.getUserById(id).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data?.asModel())

                is Resource.Error -> Resource.Error(res.error)
            }
        }.catch {
            emit(Resource.Error(it.toErrorType()))
        }

    override fun deleteUserById(id: Int) =
        userEntityDatabaseRepository.deleteUserById(id).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data)

                is Resource.Error -> Resource.Error(res.error)
            }
        }.catch {
            emit(Resource.Error(it.toErrorType()))
        }
}