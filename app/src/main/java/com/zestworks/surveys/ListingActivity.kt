package com.zestworks.surveys

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.zestworks.surveys.di.AppComponentProvider
import com.zestworks.surveys.viewmodels.SurveysViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListingActivity : AppCompatActivity() {

    private lateinit var surveysViewModel: SurveysViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        surveysViewModel = ViewModelProviders.of(this).get(SurveysViewModel::class.java)
        AppComponentProvider.instance.appComponentInstance.inject(surveysViewModel)
        surveysViewModel.load().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.d("mainactivity", it.toString())
        })
    }
}
