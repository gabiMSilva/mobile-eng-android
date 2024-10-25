package com.gmsilva.basiccomponents

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDI {
    @Singleton
    @Provides
    fun providesTaskNetworkLayer() = TaskNetworkLayer()

    @Singleton
    @Provides
    fun providesTaskDataSource(taskNetworkLayer: TaskNetworkLayer) = TaskDataSource(taskNetworkLayer)

    @Singleton
    @Provides
    fun providesTaskRepository(taskDataSource: TaskDataSource) = TaskRepository(taskDataSource)
}