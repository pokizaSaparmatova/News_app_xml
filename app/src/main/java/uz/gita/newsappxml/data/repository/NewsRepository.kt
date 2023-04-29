package uz.gita.newsappxml.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.data.remote.model.Article

interface NewsRepository {
    suspend fun latestNews(): Flow<PagingData<Article>>
    suspend fun topHeadlines(): Flow<PagingData<Article>>
    suspend fun categorizedHeadlines(category:String):Flow<List<Article>>
    suspend fun check(title: String?): ArticleEntity?

    suspend fun search(search: String): Flow<PagingData<Article>>

    fun bookmarks(): Flow<List<ArticleEntity>>
    fun searchBookmarks(search: String): Flow<List<ArticleEntity>>

    suspend fun bookmarkArticle(article: ArticleEntity)
    suspend fun unBookmarkArticle(article: ArticleEntity)
}