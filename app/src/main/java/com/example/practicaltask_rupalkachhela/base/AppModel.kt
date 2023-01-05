package com.example.practicaltask_rupalkachhela.base

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.practicaltask_rupalkachhela.roomdb.ATMDao
import com.example.practicaltask_rupalkachhela.roomdb.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModel {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase")
            .allowMainThreadQueries()
            .build()


    @Provides
    fun providesPostDao(postDatabase: AppDatabase): ATMDao = postDatabase.getAmountDao()


}