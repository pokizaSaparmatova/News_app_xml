package uz.gita.newsappxml.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappxml.domain.useCase.*
import uz.gita.newsappxml.domain.useCase.impl.*

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bookmarkOperations(impl: BookmarkOperationsImpl):BookmarkOperations

    @Binds
    fun bookmarks(impl: BookmarksImpl):Bookmarks

    @Binds
    fun latestNews(impl: LatestNewsImpl):LatestNews

    @Binds
    fun searchBookmarks(impl: SearchBookmarksImpl):SearchBookmarks

    @Binds
    fun searchNews(impl:SearchNewsImpl):SearchNews

    @Binds
    fun topHeadlines(impl:TopHeadlinesImpl):TopHeadlines

    @Binds
    fun check(impl:CheckImpl):Check

    @Binds
    fun categorized(impl:CategorizedHeadlineImpl):CategorizedHeadline
}