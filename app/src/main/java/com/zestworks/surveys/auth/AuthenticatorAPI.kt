package com.zestworks.surveys.auth


interface AuthenticatorAPI {
    fun getToken(): String
    fun performSignIn(onComplete: () -> Unit)
}