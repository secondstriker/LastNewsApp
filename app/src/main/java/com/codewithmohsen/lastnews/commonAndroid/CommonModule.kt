package com.codewithmohsen.lastnews.commonAndroid

import com.codewithmohsen.lastnews.common.Config
import com.codewithmohsen.lastnews.common.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {

    @Binds
    abstract fun bindConfig(configImpl: ConfigImpl): Config

    @Binds
    abstract fun bindLogger(loggerImpl: LoggerImpl): Logger
}