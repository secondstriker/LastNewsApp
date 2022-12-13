package com.codewithmohsen.common

interface BetterLogger: Logger {
    fun d(throwable: Throwable?)
}