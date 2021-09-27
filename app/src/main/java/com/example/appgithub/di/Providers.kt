package com.example.appgithub.di

import com.example.appgithub.service.GithubService
import com.example.appgithub.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class Providers {

    @Provides
    fun provideGithubService(): GithubService = RetrofitService.getGithubService()


}