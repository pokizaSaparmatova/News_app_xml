package uz.gita.newsappxml.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.utils.PAGE_SIZE
import java.io.IOException

class TopHeadlinesDataSource(
    private val newsApi: NewsApi
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1

        return try {
            val response = newsApi.topHeadlines(
                pageSize = PAGE_SIZE,
                page = position
            )

            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position > 1) position - 1 else null,
                nextKey = if (position < response.body()!!.totalResults / PAGE_SIZE) position + 1 else null
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}