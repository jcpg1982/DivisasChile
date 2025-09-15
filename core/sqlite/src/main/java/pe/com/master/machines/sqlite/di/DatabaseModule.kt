package pe.com.master.machines.sqlite.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Callback
import androidx.room.RoomDatabase.JournalMode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.sqlite.api.DataBase
import pe.com.master.machines.sqlite.utils.Constants
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): DataBase =
        Room.databaseBuilder(context, DataBase::class.java, Constants.DATA_BASE)
            .addCallback(sRoomDatabaseCallback)
            .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
            .build()

    private val sRoomDatabaseCallback: Callback = object : Callback() {}
}