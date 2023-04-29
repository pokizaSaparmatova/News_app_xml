package uz.gita.newsappxml.presenter.viewModel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article

interface HomeViewModel {
    val latest: Flow<PagingData<Article>>
    val topHeadlines: Flow<List<Article>>

    fun categorizedHeadlines(category: String)
    fun articleScreen(article: Article)
}