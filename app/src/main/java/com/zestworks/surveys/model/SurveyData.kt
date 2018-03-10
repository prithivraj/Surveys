package com.zestworks.surveys.model
import com.google.gson.annotations.SerializedName

data class SurveyData(
		@SerializedName("id") val id: String = "",
		@SerializedName("title") val title: String = "",
		@SerializedName("description") val description: String = "",
		@SerializedName("access_code_prompt") val accessCodePrompt: Any = Any(),
		@SerializedName("thank_email_above_threshold") val thankEmailAboveThreshold: String = "",
		@SerializedName("thank_email_below_threshold") val thankEmailBelowThreshold: String = "",
		@SerializedName("footer_content") val footerContent: String = "",
		@SerializedName("is_active") val isActive: Boolean = false,
		@SerializedName("cover_image_url") val coverImageUrl: String = "",
		@SerializedName("cover_background_color") val coverBackgroundColor: Any = Any(),
		@SerializedName("type") val type: String = "",
		@SerializedName("created_at") val createdAt: String = "",
		@SerializedName("active_at") val activeAt: String = "",
		@SerializedName("inactive_at") val inactiveAt: Any = Any(),
		@SerializedName("survey_version") val surveyVersion: Int = 0,
		@SerializedName("short_url") val shortUrl: String = "",
		@SerializedName("language_list") val languageList: List<String> = listOf(),
		@SerializedName("default_language") val defaultLanguage: String = "",
		@SerializedName("tag_list") val tagList: String = "",
		@SerializedName("is_access_code_required") val isAccessCodeRequired: Boolean = false,
		@SerializedName("is_access_code_valid_required") val isAccessCodeValidRequired: Boolean = false,
		@SerializedName("access_code_validation") val accessCodeValidation: String = "",
		@SerializedName("theme") val theme: Theme = Theme(),
		@SerializedName("questions") val questions: List<Question> = listOf()
)

data class Theme(
		@SerializedName("color_active") val colorActive: String = "",
		@SerializedName("color_inactive") val colorInactive: String = "",
		@SerializedName("color_question") val colorQuestion: String = "",
		@SerializedName("color_answer_normal") val colorAnswerNormal: String = "",
		@SerializedName("color_answer_inactive") val colorAnswerInactive: String = ""
)

data class Question(
		@SerializedName("id") val id: String = "",
		@SerializedName("text") val text: String = "",
		@SerializedName("help_text") val helpText: Any = Any(),
		@SerializedName("display_order") val displayOrder: Int = 0,
		@SerializedName("short_text") val shortText: String = "",
		@SerializedName("pick") val pick: String = "",
		@SerializedName("display_type") val displayType: String = "",
		@SerializedName("is_mandatory") val isMandatory: Boolean = false,
		@SerializedName("correct_answer_id") val correctAnswerId: Any = Any(),
		@SerializedName("facebook_profile") val facebookProfile: Any = Any(),
		@SerializedName("twitter_profile") val twitterProfile: Any = Any(),
		@SerializedName("image_url") val imageUrl: String = "",
		@SerializedName("cover_image_url") val coverImageUrl: String = "",
		@SerializedName("cover_image_opacity") val coverImageOpacity: Double = 0.0,
		@SerializedName("cover_background_color") val coverBackgroundColor: Any = Any(),
		@SerializedName("is_shareable_on_facebook") val isShareableOnFacebook: Boolean = false,
		@SerializedName("is_shareable_on_twitter") val isShareableOnTwitter: Boolean = false,
		@SerializedName("font_face") val fontFace: Any = Any(),
		@SerializedName("font_size") val fontSize: Any = Any(),
		@SerializedName("tag_list") val tagList: String = "",
		@SerializedName("answers") val answers: List<Any> = listOf()
)