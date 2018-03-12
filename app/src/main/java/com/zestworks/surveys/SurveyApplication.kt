package com.zestworks.surveys

import android.app.Application
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zestworks.surveys.di.AppComponentProvider


class SurveyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        AppComponentProvider.instance.initialize(applicationContext)
        Logger.addLogAdapter(AndroidLogAdapter())
        Stetho.initializeWithDefaults(this)
    }
}