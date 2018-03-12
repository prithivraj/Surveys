package com.zestworks.surveys.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zestworks.surveys.model.SurveyData
import io.reactivex.Flowable

@Dao
interface SurveyListDAO {

    @Query("DELETE FROM surveydata")
    fun clearSurveys()

    @Query("SELECT * FROM surveydata")
    fun getSurveys(): Flowable<List<SurveyData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllSurveys(survey: List<SurveyData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSurvey(survey: SurveyData)
}