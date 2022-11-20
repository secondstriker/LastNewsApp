package com.codewithmohsen.lastnews.clean.commonAndroid

import com.codewithmohsen.lastnews.clean.common.Config
import javax.inject.Inject

class ConfigImpl @Inject constructor() : Config {

    override val longRunningThreshold: Long =
        com.codewithmohsen.lastnews.clean.commonAndroid.Config.LONG_LOADING_THRESHOLD
}