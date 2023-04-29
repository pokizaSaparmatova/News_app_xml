package uz.gita.newsappxml.presenter.viewModel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article

interface SearchViewModel {
    val searchFlow: Flow<PagingData<Article>>
    fun searchNews(search:String)
}