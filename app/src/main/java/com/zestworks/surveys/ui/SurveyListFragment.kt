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
import com.zestworks.surveys.auth.AuthenticatorAPI
import com.zestworks.surveys.di.AppComponentProvider
import com.zestworks.surveys.viewmodels.ProgressBarStatus
import com.zestworks.surveys.viewmodels.SurveysViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_listing.*
import javax.inject.Inject


class SurveyListFragment : Fragment() {

    private lateinit var surveysViewModel: SurveysViewModel
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var subscriptionsWithViewModel = CompositeDisposable()

    @Inject
    lateinit var authenticatorAPI: AuthenticatorAPI

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerAdapter = RecyclerAdapter({
            if (!subscriptionsWithViewModel.isDisposed) {
                subscriptionsWithViewModel.dispose()
                subscriptionsWithViewModel = CompositeDisposable()
            }
            surveysViewModel.takeSurveyClicked(it)
            val singleSurveyFragment = SingleSurveyFragment()
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, singleSurveyFragment).addToBackStack(null).commit()
        })

        recycler_view.adapter = recyclerAdapter
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(recycler_view)
        recycler_view.addItemDecoration(PageIndicator(context!!))

        surveysViewModel = ViewModelProviders.of(activity!!).get(SurveysViewModel::class.java)
        AppComponentProvider.instance.appComponentInstance.inject(surveysViewModel)
        AppComponentProvider.instance.appComponentInstance.inject(this)

        authenticatorAPI.performSignIn({
            refresh.setOnClickListener({
                if (!subscriptionsWithViewModel.isDisposed) {
                    subscriptionsWithViewModel.dispose()
                    subscriptionsWithViewModel = CompositeDisposable()
                }
                loadSurveys()
            })
            loadSurveys()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        retainInstance = true
        return inflater.inflate(R.layout.activity_listing, container, false)
    }

    private fun loadSurveys() {
        subscriptionsWithViewModel.add(surveysViewModel.getLoaderStateStream().observeOn(AndroidSchedulers.mainThread()).subscribe({
            when (it) {
                ProgressBarStatus.LOADING -> loader?.visibility = View.VISIBLE
                ProgressBarStatus.LOADED -> loader?.visibility = View.GONE
            }
        }))

        subscriptionsWithViewModel.add(surveysViewModel.getSurveyListStream().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.isEmpty()) {
                return@subscribe
            }
            recyclerAdapter.surveyDataList = it
            recyclerAdapter.notifyDataSetChanged()
        }))
    }
}