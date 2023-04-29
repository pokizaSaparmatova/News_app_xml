package uz.gita.newsappxml.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.utils.PAGE_SIZE
import java.io.IOException

class EverythingDataSource(
    private val newsApi: NewsApi,
    private val query: String
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPageNumber = params.key ?: 1
        var response = newsApi.everything(
            search = query,
            pageSize = PAGE_SIZE,
            pageCount = currentPageNumber
        )

        if (response.code() != 426) {
            response = newsApi.everything(
                search = query,
                pageSize = PAGE_SIZE,
                pageCount = currentPageNumber
            )
        }
        if (response.body() != null) {
            return try {
                LoadResult.Page(
                    data = response.body()!!.articles,
                    prevKey = if (currentPageNumber > 1) currentPageNumber - 1 else null,
                    nextKey = if (currentPageNumber < response.body()!!.totalResults / PAGE_SIZE) currentPageNumber + 1 else null
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        } else {
            val newResponse = newsApi.everything(
                search = query,
                pageSize = PAGE_SIZE,
                pageCount = 2
            )

            return try {
                LoadResult.Page(
                    data = newResponse.body()!!.articles,
                    prevKey = if (currentPageNumber > 1) currentPageNumber - 1 else null,
                    nextKey = if (currentPageNumber < newResponse.body()!!.totalResults / PAGE_SIZE) currentPageNumber + 1 else null
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: HttpException) {
                LoadResult.Error(e)
            }
        }

    }
}