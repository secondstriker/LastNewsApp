package com.codewithmohsen.commonandroid.di

import com.codewithmohsen.common.BetterLogger
import com.codewithmohsen.common.Config
import com.codewithmohsen.common.Logger
import com.codewithmohsen.commonandroid.BetterLoggerImpl
import com.codewithmohsen.commonandroid.ConfigImpl
import com.codewithmohsen.commonandroid.LoggerImpl
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

    @Binds
    abstract fun bindBetterLogger(betterLogger: BetterLoggerImpl): BetterLogger
}