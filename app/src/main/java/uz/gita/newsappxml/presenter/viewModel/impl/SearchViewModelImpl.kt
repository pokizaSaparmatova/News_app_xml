package uz.gita.newsappxml.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.domain.useCase.SearchNews
import uz.gita.newsappxml.presenter.viewModel.SearchViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val searchNewsUC:SearchNews
): SearchViewModel, ViewModel() {
    override val searchFlow = MutableStateFlow<PagingData<Article>>(PagingData.empty())

    override fun searchNews(search: String) {
        viewModelScope.launch {
            searchNewsUC.search(search).collectLatest {
                searchFlow.emit(it)
            }
        }
    }
}