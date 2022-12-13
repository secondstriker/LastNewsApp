package com.codewithmohsen.commonandroid

import javax.inject.Inject
import com.codewithmohsen.common.Config

class ConfigImpl @Inject constructor() : Config {

    override val longRunningThreshold: Long =
        com.codewithmohsen.commonandroid.Config.LONG_LOADING_THRESHOLD
}