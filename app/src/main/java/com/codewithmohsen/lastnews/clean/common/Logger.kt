package com.codewithmohsen.lastnews.clean.common

interface Logger {
    fun d(message: String, tag: String? = null)
    fun e(message: String, tag: String? = null)
    fun v(message: String, tag: String? = null)
}