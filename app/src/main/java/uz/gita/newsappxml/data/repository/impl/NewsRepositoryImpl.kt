package uz.gita.newsappxml.data.repository.impl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappxml.data.local.room.dao.NewsDao
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.data.remote.EverythingDataSource
import uz.gita.newsappxml.data.remote.NewsApi
import uz.gita.newsappxml.data.remote.TopHeadlinesDataSource
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.utils.PAGE_SIZE
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun latestNews(): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            EverythingDataSource(newsApi, "")
        }.flow


    override suspend fun topHeadlines(): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            TopHeadlinesDataSource(newsApi)
        }.flow

    override suspend fun categorizedHeadlines(category: String): Flow<List<Article>> = flow {
        val response = newsApi.topHeadlines(category = category)

        if (response.isSuccessful) {
            Log.d("@@@", "response - ${response.isSuccessful}")
            Log.d("@@@", "response - ${response.raw()}")
            Log.d("@@@", "response count - ${response.body()!!.articles}")

            response.body()?.articles?.let {
                emit(it)
            }
        } else {
            Log.d("@@@", "response - ${response.raw()}")
        }
    }.flowOn(IO).catch { Log.d("@@@", "${it.message}") }

    override suspend fun check(title: String?): ArticleEntity? {
        return newsDao.check(title)
    }

    override suspend fun search(search: String): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            EverythingDataSource(newsApi, search)
        }.flow

    override fun bookmarks(): Flow<List<ArticleEntity>> {
        return newsDao.bookmarks()
    }

    override fun searchBookmarks(search: String): Flow<List<ArticleEntity>> {
        return newsDao.searchBookmark(search)
    }


    override suspend fun bookmarkArticle(article: ArticleEntity) {
        newsDao.insert(article)
    }

    override suspend fun unBookmarkArticle(article: ArticleEntity) {
        newsDao.delete(article)
    }
}