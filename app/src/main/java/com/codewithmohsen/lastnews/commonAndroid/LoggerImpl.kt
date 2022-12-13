package com.codewithmohsen.lastnews.commonAndroid

import com.codewithmohsen.lastnews.common.Logger
import timber.log.Timber
import javax.inject.Inject

open class LoggerImpl @Inject constructor(): Logger {
    override fun d(message: String, tag: String?) {
        if(tag != null) Timber.tag(tag).d(message)
        else Timber.d(message)
    }

    override fun e(message: String, tag: String?) {
        if(tag != null) Timber.tag(tag).e(message)
        else Timber.e(message)
    }

    override fun v(message: String, tag: String?) {
        if(tag != null) Timber.tag(tag).v(message)
        else Timber.v(message)
    }
}