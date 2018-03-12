package com.zestworks.surveys.di

import android.arch.persistence.room.Room
import android.content.Context
import com.zestworks.surveys.api.SurveyApi
import com.zestworks.surveys.database.SurveyListDatabase
import com.zestworks.surveys.repository.OfflineFirstRepository
import com.zestworks.surveys.repository.SurveyRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class AppModule(private val context: Context) {
    //TODO remove context if it's not needed.

    @Provides
    @PerApp
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://nimbl3-survey-api.herokuapp.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @PerApp
    fun provideApiService(retrofit: Retrofit): SurveyApi{
        return retrofit.create(SurveyApi::class.java)
    }

    @Provides
    @PerApp
    fun provideDatabase(): SurveyListDatabase {
        //return Room.inMemoryDatabaseBuilder(context, SurveyListDatabase::class.java).build()
        return Room.databaseBuilder(context, SurveyListDatabase::class.java, "SurveyList.db").build()
    }

    @Provides
    @PerApp
    fun provideRepository(surveyDatabase: SurveyListDatabase, surveyApi: SurveyApi): SurveyRepository {
        return OfflineFirstRepository(surveyApi, surveyDatabase)
    }
}