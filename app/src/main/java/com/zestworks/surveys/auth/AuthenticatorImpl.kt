package com.zestworks.surveys.auth

import android.content.SharedPreferences
import androidx.content.edit
import com.zestworks.surveys.BuildConfig


class AuthenticatorImpl(private val sharedPreferences: SharedPreferences) : AuthenticatorAPI {

    private lateinit var accessToken: String

    override fun getToken(): String {
        return accessToken
    }

    override fun performSignIn(onComplete: () -> Unit) {

        // Checking if already signed in
        var token = sharedPreferences.getString("accessToken", "")

        if (token.isEmpty()) {
            // Assuming a fresh sign in here.
            token = BuildConfig.ACCESSTOKEN
            sharedPreferences.edit {
                putString("accessToken", token)
            }
        }
        accessToken = token
        onComplete.invoke()
    }
}