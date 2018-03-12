package com.zestworks.surveys.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zestworks.surveys.R

class ListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        supportFragmentManager.beginTransaction().replace(R.id.container, SurveyListFragment()).commitNow()
    }
}
