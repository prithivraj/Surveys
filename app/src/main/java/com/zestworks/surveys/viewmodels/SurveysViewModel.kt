package com.zestworks.surveys.viewmodels

import android.arch.lifecycle.ViewModel
import com.zestworks.surveys.model.SurveyData
import com.zestworks.surveys.repository.SurveyRepository
import io.reactivex.Flowable
import javax.inject.Inject


class SurveysViewModel : ViewModel() {

    @Inject
    lateinit var repository: SurveyRepository

    private var data: List<SurveyData>? = null

    fun load(): Flowable<List<SurveyData>> {
        return repository.getSurveyList().map {
            data = it
            return@map it
        }
    }

}