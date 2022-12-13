package com.codewithmohsen.lastnews.common

interface BetterLogger: Logger {
    fun d(throwable: Throwable?)
}