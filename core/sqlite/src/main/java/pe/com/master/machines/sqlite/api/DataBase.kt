package pe.com.master.machines.sqlite.api

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.com.master.machines.sqlite.model.dao.DaoUserEntity
import pe.com.master.machines.sqlite.model.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun userEntityDao(): DaoUserEntity

}