package com.zestworks.surveys.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zestworks.surveys.R
import com.zestworks.surveys.di.AppComponentProvider
import com.zestworks.surveys.viewmodels.SurveysViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_listing.*


class SurveyListFragment : Fragment() {
    private lateinit var surveysViewModel: SurveysViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var networkRequestDisposible: Disposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerAdapter = RecyclerAdapter({
            if (!networkRequestDisposible.isDisposed) {
                networkRequestDisposible.dispose()
            }
            val singleSurveyFragment = SingleSurveyFragment()
            val bundle = Bundle()
            bundle.putInt("surveyIndex", it)
            singleSurveyFragment.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, singleSurveyFragment).addToBackStack(null).commit()
        })
        recycler_view.adapter = recyclerAdapter
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(recycler_view)
        recycler_view.addItemDecoration(PageIndicator(context!!))

        surveysViewModel = ViewModelProviders.of(activity!!).get(SurveysViewModel::class.java)
        AppComponentProvider.instance.appComponentInstance.inject(surveysViewModel)

        refresh.setOnClickListener({
            loader?.visibility = View.VISIBLE
            recycler_view.visibility = View.INVISIBLE
            if (!networkRequestDisposible.isDisposed) {
                networkRequestDisposible.dispose()
            }
            loadSurveys()
        })

        loadSurveys()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_listing, container, false)
    }

    private fun loadSurveys() {
        networkRequestDisposible = surveysViewModel.load().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.isEmpty()) {
                return@subscribe
            }
            loader?.visibility = View.GONE
            recycler_view?.visibility = View.VISIBLE
            recyclerAdapter.surveyDataList = it
            recyclerAdapter.notifyDataSetChanged()
        })
    }
}