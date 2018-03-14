package com.zestworks.surveys.api

import com.zestworks.surveys.model.SurveyData
import io.reactivex.Observable
import retrofit2.http.GET


interface SurveyApi {
    @GET("/surveys.json")
    fun getAllSurveys(): Observable<Array<SurveyData>>
}