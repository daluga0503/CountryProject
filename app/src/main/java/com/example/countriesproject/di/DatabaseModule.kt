package com.example.countriesproject.di

import android.content.Context
import com.example.countriesproject.data.db.CountryDao
import com.example.countriesproject.data.db.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideCountryDatabase(@ApplicationContext context: Context):CountryDatabase{
        return CountryDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCountryDao(database:CountryDatabase):CountryDao{
        return database.countryDao()
    }
}