package com.example.lastfmuselessapp.injection.data

import com.example.lastfmuselessapp.config.Config
import com.example.lastfmuselessapp.domain.provider.SearchCategoryProvider
import com.example.lastfmuselessapp.domain.time.TaskScheduler
import com.example.lastfmuselessapp.provider.SearchCategoryProviderImpl
import com.example.lastfmuselessapp.time.TaskSchedulerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSearchCategoryProvider(): SearchCategoryProvider {
        return SearchCategoryProviderImpl(
            Config.DEFAULT_SEARCH_CATEGORY,
            Config.AVAILABLE_SEARCH_CATEGORIES
        )
    }

    @Provides
    @Singleton
    fun provideTaskScheduler(): TaskScheduler {
        return TaskSchedulerImpl()
    }
}