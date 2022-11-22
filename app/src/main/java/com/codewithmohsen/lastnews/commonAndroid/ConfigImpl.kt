package com.codewithmohsen.lastnews.commonAndroid

import com.codewithmohsen.lastnews.common.Config
import javax.inject.Inject

class ConfigImpl @Inject constructor() : Config {

    override val longRunningThreshold: Long =
        com.codewithmohsen.lastnews.commonAndroid.Config.LONG_LOADING_THRESHOLD
}