package com.zestworks.surveys

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.View
import com.zestworks.surveys.di.AppComponentProvider
import com.zestworks.surveys.viewmodels.SurveysViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_listing.*

class ListingActivity : AppCompatActivity() {

    private lateinit var surveysViewModel: SurveysViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerAdapter = RecyclerAdapter()
        recycler_view.adapter = recyclerAdapter

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(recycler_view)

        surveysViewModel = ViewModelProviders.of(this).get(SurveysViewModel::class.java)
        AppComponentProvider.instance.appComponentInstance.inject(surveysViewModel)
        surveysViewModel.load().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            loader.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
            recyclerAdapter.surveyDataList = it.toList()
            recyclerAdapter.notifyDataSetChanged()
        })
    }
}
