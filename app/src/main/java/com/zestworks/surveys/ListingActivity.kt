package com.zestworks.surveys

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zestworks.surveys.di.AppComponentProvider
import com.zestworks.surveys.viewmodels.SurveysViewModel

class ListingActivity : AppCompatActivity() {

    private lateinit var surveysViewModel : SurveysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        surveysViewModel = ViewModelProviders.of(this).get(SurveysViewModel::class.java)
        AppComponentProvider.instance.appComponentInstance.inject(surveysViewModel)
        surveysViewModel.load()
    }
}
