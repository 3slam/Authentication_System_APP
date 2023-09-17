package com.example.straterproject.di

import com.example.data.repo.AuthenticationRepoImpl
import com.example.domain.repo.AuthenticationRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(auth :FirebaseAuth):AuthenticationRepo  {
        return AuthenticationRepoImpl(auth)
    }
}