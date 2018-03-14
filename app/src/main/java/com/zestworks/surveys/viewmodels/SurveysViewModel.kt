package com.zestworks.surveys.viewmodels

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.zestworks.surveys.model.SurveyData
import com.zestworks.surveys.repository.SurveyRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject


class SurveysViewModel : ViewModel() {

    @Inject
    lateinit var repository: SurveyRepository

    private val singleSurveyStream: BehaviorRelay<SurveyData> = BehaviorRelay.create()

    private var data: List<SurveyData>? = null

    fun load(): Flowable<List<SurveyData>> {
        return repository.getSurveyList().map {
            data = it
            return@map it
        }
    }

    fun getSingleDisplayStream(): Observable<SurveyData> {
        return singleSurveyStream
    }

    fun event_takeSurvey(index: Int) {
        singleSurveyStream.accept(data?.get(index))
    }

}