package pe.com.master.machines.sqlite.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.sqlite.model.entity.UserEntity

@Dao
interface DaoUserEntity {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM UserEntity WHERE email = :email)")
    suspend fun isEmailTaken(email: String): Boolean

    @Query("SELECT * FROM UserEntity WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?>

    @Query("DELETE FROM UserEntity WHERE id = :id")
    suspend fun deleteUserById(id: Int)

}