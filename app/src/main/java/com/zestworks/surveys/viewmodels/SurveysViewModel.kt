package com.zestworks.surveys.viewmodels

import android.arch.lifecycle.ViewModel
import com.zestworks.surveys.api.SurveyApi
import com.zestworks.surveys.model.SurveyData
import io.reactivex.Observable
import javax.inject.Inject


class SurveysViewModel : ViewModel() {

    @Inject
    lateinit var surveyApi: SurveyApi

    private var data: Array<SurveyData>? = null

    fun load(): Observable<Array<SurveyData>> {
        return surveyApi.getAllSurveys().doOnEach({
            data = it.value
        })
    }

}