package pe.com.master.machines.sqlite.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.sqlite.model.entity.UserEntity

interface UserEntityDatabaseRepository {

    fun insertUser(user: UserEntity): Flow<Resource<Unit>>
    fun getAllUsers(): Flow<Resource<List<UserEntity>>>
    fun isEmailTaken(email: String): Flow<Resource<Boolean>>
    fun login(email: String, password: String): Flow<Resource<UserEntity?>>
    fun getUserById(id: Int): Flow<Resource<UserEntity?>>
    fun deleteUserById(id: Int): Flow<Resource<Unit>>
}