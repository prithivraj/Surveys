package com.zestworks.surveys.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zestworks.surveys.R
import com.zestworks.surveys.model.SurveyData

class RecyclerAdapter : RecyclerView.Adapter<RecyclerHolder>() {

    internal var surveyDataList: List<SurveyData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_holder, parent, false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val surveyData = surveyDataList[position]
        holder.surveyTitle.text = surveyData.title
        holder.surveyDescription.text = surveyData.description
        Picasso.get().load(surveyData.coverImageUrl).into(holder.surveyBackground)
    }

    override fun getItemCount(): Int {
        return surveyDataList.size
    }
}

class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var surveyBackground: ImageView = itemView.findViewById(R.id.survey_bgd)
    var surveyTitle: TextView = itemView.findViewById(R.id.survey_title)
    var surveyDescription: TextView = itemView.findViewById(R.id.survey_description)
}