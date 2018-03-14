package com.zestworks.surveys.repository

import com.zestworks.surveys.BuildConfig
import com.zestworks.surveys.api.SurveyApi
import com.zestworks.surveys.database.SurveyListDatabase
import com.zestworks.surveys.model.SurveyData
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class OfflineFirstRepository(private val surveyApi: SurveyApi, private val surveyDatabase: SurveyListDatabase) : SurveyRepository {

    override fun getSurveyList(): Flowable<List<SurveyData>> {
        return surveyDatabase.surveyListDAO().getSurveys().doOnSubscribe {
            surveyApi.getAllSurveys(BuildConfig.ACCESSTOKEN).subscribeOn(Schedulers.io()).subscribe(object : Observer<Array<SurveyData>> {
                override fun onComplete() {
                    //TODO Wrap getSurveyList's return type as a state object and supply error states along with data.
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Array<SurveyData>) {
                    surveyDatabase.surveyListDAO().addAllSurveys(t.asList())
                }

                override fun onError(e: Throwable) {
                    //TODO Wrap getSurveyList's return type as a state object and supply error states along with data.
                }
            })
        }
    }
}