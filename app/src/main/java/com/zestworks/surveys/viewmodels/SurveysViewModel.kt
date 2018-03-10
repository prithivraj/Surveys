package com.zestworks.surveys.viewmodels

import android.arch.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.zestworks.surveys.api.SurveyApi
import com.zestworks.surveys.model.SurveyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class SurveysViewModel : ViewModel() {

    @Inject
    lateinit var surveyApi: SurveyApi

    fun load(){
        val allEmployees = surveyApi.getAllEmployees()
        allEmployees.enqueue(object : Callback<Array<SurveyData>>{
            override fun onFailure(call: Call<Array<SurveyData>>?, t: Throwable?) {
                Logger.e(t?.message)
            }

            override fun onResponse(call: Call<Array<SurveyData>>?, response: Response<Array<SurveyData>>?) {
                Logger.d(response)
            }
        })
    }

}