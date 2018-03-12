package com.zestworks.surveys.repository

import com.zestworks.surveys.model.SurveyData
import io.reactivex.Flowable


interface SurveyRepository {
    fun getSurveyList(): Flowable<List<SurveyData>>
}