package com.zestworks.surveys.api

import com.zestworks.surveys.model.SurveyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface SurveyApi {
    @GET("/surveys.json")
    fun getAllEmployees(@Query("access_token") accessToken : String = "d9584af77d8c0d6622e2b3c554ed520b2ae64ba0721e52daa12d6eaa5e5cdd93"): Call<Array<SurveyData>>
}