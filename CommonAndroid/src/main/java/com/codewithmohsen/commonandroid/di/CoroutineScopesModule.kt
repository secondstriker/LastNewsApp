package com.codewithmohsen.commonandroid.di

import com.codewithmohsen.common.BetterLogger
import com.codewithmohsen.common.di.ApplicationScope
import com.codewithmohsen.common.di.DefaultDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

    /**
     * get scope from application to do something independently from our coroutine scopes.
     */
    @Singleton
    @ApplicationScope
    @Provides
    fun providesCoroutineScopeForApplication(
        logger: BetterLogger,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher
            + CoroutineName("ExternalCoroutineScope") +
            CoroutineExceptionHandler { _, throwable ->
            logger.d(throwable)
    })

}