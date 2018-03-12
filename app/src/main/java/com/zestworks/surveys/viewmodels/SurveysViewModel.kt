package com.zestworks.surveys.viewmodels

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.zestworks.surveys.model.SurveyData
import com.zestworks.surveys.repository.SurveyRepository
import io.reactivex.Flowable
import javax.inject.Inject


class SurveysViewModel : ViewModel() {

    @Inject
    lateinit var repository: SurveyRepository

    private val singleSurveyStream: PublishRelay<SurveyData> = PublishRelay.create<SurveyData>()

    private var data: List<SurveyData>? = null

    fun load(): Flowable<List<SurveyData>> {
        return repository.getSurveyList().map {
            data = it
            return@map it
        }
    }

    fun getSingleDisplayStream(): PublishRelay<SurveyData> {
        return singleSurveyStream
    }

    fun event_takeSurvey(index: Int) {
        singleSurveyStream.accept(data?.get(index))
    }

}