package com.zestworks.surveys.viewmodels

import com.zestworks.surveys.model.SurveyData
import com.zestworks.surveys.repository.SurveyRepository
import com.zestworks.surveys.viewmodels.matchers.shouldBe
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SurveyViewModelTest {

    @InjectMocks
    lateinit var viewModel: SurveysViewModel

    @Mock
    lateinit var repository: SurveyRepository

    lateinit var surveyList: List<SurveyData>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val surveyData = SurveyData("id1", "Hotel Chennai", "Welcome!", "http://google.com")
        val surveyData1 = SurveyData("id2", "Hotel Bangalore", "Welcome!", "http://gmail.com")
        val surveyData2 = SurveyData("id3", "Hotel California", "Welcome!", "http://facebook.com")
        val surveyData3 = SurveyData("id4", "Hotel Thailand", "Welcome!", "http://reddit.com")
        val surveyData4 = SurveyData("id5", "Hotel Phi Phi", "Welcome!", "http://uber.com")
        surveyList = listOf<SurveyData>(surveyData, surveyData1, surveyData2, surveyData3, surveyData4)
        Mockito.`when`(repository.getSurveyList()).thenReturn(Flowable.just(surveyList))
    }

    @Test
    fun testMock_injectedCorrectly() {
        viewModel.repository shouldBe repository
    }

    @Test
    fun testGetSurveyList_emitsCorrectly() {
        val testObserver = viewModel.load().test()
        testObserver.assertNoErrors()
        testObserver.values()[0] shouldBe surveyList
    }

    @Test
    fun testSingleSurvey_emitsCorrectSurvey() {
        val selectedIndex = 0
        viewModel.load().subscribe()
        val testSingleSurveyObserver = viewModel.getSingleDisplayStream().test()
        viewModel.event_takeSurvey(selectedIndex)
        testSingleSurveyObserver.assertNoErrors()
        testSingleSurveyObserver.values()[selectedIndex] shouldBe surveyList[selectedIndex]
    }

}