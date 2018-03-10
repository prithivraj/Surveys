package com.zestworks.surveys.di

import android.content.Context
import com.zestworks.surveys.api.SurveyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
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
                .build()
    }

    @Provides
    @PerApp
    fun provideApiService(retrofit: Retrofit): SurveyApi{
        return retrofit.create(SurveyApi::class.java)
    }
}