package com.zestworks.surveys.ui


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zestworks.surveys.R
import com.zestworks.surveys.viewmodels.SurveysViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_single_survey.*

class SingleSurveyFragment : Fragment() {

    private lateinit var surveysViewModel: SurveysViewModel
    private lateinit var disposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        surveysViewModel = ViewModelProviders.of(activity!!).get(SurveysViewModel::class.java)
        bindToDataStreams()
        return inflater.inflate(R.layout.fragment_single_survey, container, false)
    }

    private fun bindToDataStreams() {
        disposable = surveysViewModel.getSingleDisplayStream().subscribe({
            single_title?.text = it.title
            single_description?.text = it.description
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = arguments!!.getInt("surveyIndex")
        surveysViewModel.event_takeSurvey(index)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
