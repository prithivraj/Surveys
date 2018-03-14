package com.zestworks.surveys.api

import com.zestworks.surveys.model.SurveyData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface SurveyApi {
    @GET("/surveys.json")
    fun getAllSurveys(@Query("access_token") accessToken: String): Observable<Array<SurveyData>>
}