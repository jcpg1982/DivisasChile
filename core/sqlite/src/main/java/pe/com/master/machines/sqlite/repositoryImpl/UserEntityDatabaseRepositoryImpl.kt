package pe.com.master.machines.sqlite.repositoryImpl

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.sqlite.api.DataBase
import pe.com.master.machines.sqlite.model.entity.UserEntity
import pe.com.master.machines.sqlite.repository.UserEntityDatabaseRepository
import javax.inject.Inject

class UserEntityDatabaseRepositoryImpl @Inject constructor(private val database: DataBase) :
    UserEntityDatabaseRepository {

    private val userDao = database.userEntityDao()

    override fun insertUser(user: UserEntity) = flow {
        val result = userDao.insertUser(user)
        emit(Resource.Success(result))
    }

    override fun getAllUsers() = userDao.getAllUsers().map { Resource.Success(it) }

    override fun isEmailTaken(email: String) = flow {
        val result = userDao.isEmailTaken(email)
        emit(Resource.Success(result))
    }

    override fun login(
        email: String, password: String
    ) = flow {
        val result = userDao.login(email, password)
        emit(Resource.Success(result))
    }

    override fun getUserById(id: Int) = userDao.getUserById(id).map { Resource.Success(it) }

    override fun deleteUserById(id: Int) = flow {
        val result = userDao.deleteUserById(id)
        emit(Resource.Success(result))
    }

}