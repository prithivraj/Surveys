package com.zestworks.surveys.auth

import com.zestworks.surveys.BuildConfig


class AuthenticatorImpl : AuthenticatorAPI {

    lateinit var accessToken: String

    override fun getToken(): String {
        return accessToken
    }

    override fun performSignIn(onComplete: () -> Unit) {
        accessToken = BuildConfig.ACCESSTOKEN
        onComplete.invoke()
    }
}