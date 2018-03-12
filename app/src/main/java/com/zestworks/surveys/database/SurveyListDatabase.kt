package com.zestworks.surveys.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.zestworks.surveys.model.SurveyData

@Database(entities = [(SurveyData::class)], version = 1)
abstract class SurveyListDatabase : RoomDatabase() {
    abstract fun surveyListDAO(): SurveyListDAO
}