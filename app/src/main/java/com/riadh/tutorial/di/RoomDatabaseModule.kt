package com.riadh.tutorial.di

import android.content.Context
import androidx.room.Room
import com.riadh.tutorial.room.MyDao
import com.riadh.tutorial.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideMylDao(appDatabase: RoomDatabase): MyDao {
        return appDatabase.myDao()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room.databaseBuilder(
            appContext,
            RoomDatabase::class.java,
            "room_data_base"
        ).build()
    }
}