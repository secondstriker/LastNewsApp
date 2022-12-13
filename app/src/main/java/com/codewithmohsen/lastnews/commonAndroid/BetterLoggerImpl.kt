package com.codewithmohsen.lastnews.commonAndroid

import com.codewithmohsen.lastnews.common.BetterLogger
import timber.log.Timber
import javax.inject.Inject

class BetterLoggerImpl @Inject constructor(): LoggerImpl(), BetterLogger {
    override fun d(throwable: Throwable?) {
       Timber.d(throwable)
    }
}