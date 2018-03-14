package com.zestworks.surveys.viewmodels

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.zestworks.surveys.model.SurveyData
import com.zestworks.surveys.repository.SurveyRepository
import com.zestworks.surveys.viewmodels.ProgressBarStatus.LOADED
import com.zestworks.surveys.viewmodels.ProgressBarStatus.LOADING
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject


class SurveysViewModel : ViewModel() {

    @Inject
    lateinit var repository: SurveyRepository

    private val singleSurveyStream: BehaviorRelay<SurveyData> = BehaviorRelay.create()
    private val loaderStateStream: PublishRelay<ProgressBarStatus> = PublishRelay.create()
    private var data: List<SurveyData>? = null

    fun getSurveyListStream(): Flowable<List<SurveyData>> {
        return repository.getSurveyList().doOnSubscribe {
            loaderStateStream.accept(LOADING)
        }.doAfterNext {
            if (it.isNotEmpty()) {
                loaderStateStream.accept(LOADED)
            }
        }.map {
            data = it
            return@map it
        }
    }

    fun getSingleSurveyStream(): Observable<SurveyData> {
        return singleSurveyStream
    }

    fun getLoaderStateStream(): Observable<ProgressBarStatus> {
        return loaderStateStream
    }

    fun takeSurveyClicked(index: Int) {
        singleSurveyStream.accept(data?.get(index))
    }

}