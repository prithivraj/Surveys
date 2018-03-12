package com.zestworks.surveys.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.zestworks.surveys.database.Converters

@Entity
@TypeConverters(Converters::class)
data class SurveyData(
		@PrimaryKey @SerializedName("id") var id: String = "",
		@SerializedName("title") var title: String = "",
		@SerializedName("description") var description: String = "",
		@SerializedName("cover_image_url") var coverImageUrl: String = ""
		/*@SerializedName("access_code_prompt") var accessCodePrompt: String = "",
		@SerializedName("thank_email_above_threshold") var thankEmailAboveThreshold: String = "",
		@SerializedName("thank_email_below_threshold") var thankEmailBelowThreshold: String = "",
		@SerializedName("footer_content") var footerContent: String = "",
		@SerializedName("is_active") var isActive: Boolean = false,
		@SerializedName("cover_background_color") var coverBackgroundColor: String = "",
		@SerializedName("type") var type: String = "",
		@SerializedName("created_at") var createdAt: String = "",
		@SerializedName("active_at") var activeAt: String = "",
		@SerializedName("inactive_at") var inactiveAt: String = "",
		@SerializedName("survey_version") var surveyVersion: Int = 0,
		@SerializedName("short_url") var shortUrl: String = "",
		@SerializedName("language_list") var languageList: List<String> = listOf(),
		@SerializedName("default_language") var defaultLanguage: String = "",
		@SerializedName("tag_list") var tagList: String = "",
		@SerializedName("is_access_code_required") var isAccessCodeRequired: Boolean = false,
		@SerializedName("is_access_code_valid_required") var isAccessCodeValidRequired: Boolean = false,
		@SerializedName("access_code_validation") var accessCodeValidation: String = "",
		@Embedded @SerializedName("theme") var theme: Theme = Theme(),
		@SerializedName("questions") var questions: List<Question> = listOf()*/
)
/*

@Entity
data class Theme(
		@SerializedName("color_active") var colorActive: String = "",
		@SerializedName("color_inactive") var colorInactive: String = "",
		@SerializedName("color_question") var colorQuestion: String = "",
		@SerializedName("color_answer_normal") var colorAnswerNormal: String = "",
		@SerializedName("color_answer_inactive") var colorAnswerInactive: String = ""
)

data class Question(
		@SerializedName("id") var id: String = "",
		@SerializedName("text") var text: String = "",
		@SerializedName("help_text") var helpText: String = "",
		@SerializedName("display_order") var displayOrder: Int = 0,
		@SerializedName("short_text") var shortText: String = "",
		@SerializedName("pick") var pick: String = "",
		@SerializedName("display_type") var displayType: String = "",
		@SerializedName("is_mandatory") var isMandatory: Boolean = false,
		@SerializedName("correct_answer_id") var correctAnswerId: String = "",
		@SerializedName("facebook_profile") var facebookProfile: String = "",
		@SerializedName("twitter_profile") var twitterProfile: String = "",
		@SerializedName("image_url") var imageUrl: String = "",
		@SerializedName("cover_image_url") var coverImageUrl: String = "",
		@SerializedName("cover_image_opacity") var coverImageOpacity: Double = 0.0,
		@SerializedName("cover_background_color") var coverBackgroundColor: String = "",
		@SerializedName("is_shareable_on_facebook") var isShareableOnFacebook: Boolean = false,
		@SerializedName("is_shareable_on_twitter") var isShareableOnTwitter: Boolean = false,
		@SerializedName("font_face") var fontFace: String = "",
		@SerializedName("font_size") var fontSize: String = "",
		@SerializedName("tag_list") var tagList: String = "",
		@SerializedName("answers") var answers: List<Answer> = listOf()
)


data class Answer(
		@SerializedName("id") val id: String = "",
		@SerializedName("question_id") val questionId: String = "",
		@SerializedName("text") val text: String = "",
		@SerializedName("help_text") val helpText: String = "",
		@SerializedName("input_mask_placeholder") val inputMaskPlaceholder: String = "",
		@SerializedName("short_text") val shortText: String = "",
		@SerializedName("is_mandatory") val isMandatory: Boolean = false,
		@SerializedName("is_customer_first_name") val isCustomerFirstName: Boolean = false,
		@SerializedName("is_customer_last_name") val isCustomerLastName: Boolean = false,
		@SerializedName("is_customer_title") val isCustomerTitle: Boolean = false,
		@SerializedName("is_customer_email") val isCustomerEmail: Boolean = false,
		@SerializedName("prompt_custom_answer") val promptCustomAnswer: Boolean = false,
		@SerializedName("weight") val weight: String = "",
		@SerializedName("display_order") val displayOrder: Int = 0,
		@SerializedName("display_type") val displayType: String = "",
		@SerializedName("input_mask") val inputMask: String = "",
		@SerializedName("date_constraint") val dateConstraint: String = "",
		@SerializedName("default_value") val defaultValue: String = "",
		@SerializedName("response_class") val responseClass: String = "",
		@SerializedName("reference_identifier") val referenceIdentifier: String = "",
		@SerializedName("score") val score: Int = 0,
		@SerializedName("alerts") val alerts: List<String> = listOf()
)*/
