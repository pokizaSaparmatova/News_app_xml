package uz.gita.newsappxml.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.data.repository.impl.NewsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun newsRepo(impl: NewsRepositoryImpl): NewsRepository
}