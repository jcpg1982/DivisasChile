package pe.com.master.machines.data.repository.database

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.model.model.User

interface UserDataRepository {

    fun insertUser(user: User): Flow<Resource<Unit>>
    fun getAllUsers(): Flow<Resource<List<User>>>
    fun isEmailTaken(email: String): Flow<Resource<Boolean>>
    fun login(email: String, password: String): Flow<Resource<User?>>
    fun getUserById(id: Int): Flow<Resource<User?>>
    fun deleteUserById(id: Int): Flow<Resource<Unit>>
}