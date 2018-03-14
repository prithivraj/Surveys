package com.zestworks.surveys.di

import com.zestworks.surveys.ui.SurveyListFragment
import com.zestworks.surveys.viewmodels.SurveysViewModel
import dagger.Component

@Component(modules = [(AppModule::class)])
@PerApp
interface AppComponent {
    fun inject(surveysViewModel: SurveysViewModel)
    fun inject(surveysViewModel: SurveyListFragment)
}