package com.codewithmohsen.lastnews.clean.commonAndroid

import com.codewithmohsen.lastnews.clean.common.Logger
import timber.log.Timber
import javax.inject.Inject

class LoggerImpl @Inject constructor(): Logger {
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