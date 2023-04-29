package uz.gita.newsappxml.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.domain.useCase.Bookmarks
import uz.gita.newsappxml.domain.useCase.SearchBookmarks
import uz.gita.newsappxml.presenter.viewModel.BookmarkViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModelImpl @Inject constructor(
    private val searchBookmarksUC: SearchBookmarks,
    private val bookmarksUC: Bookmarks
) : BookmarkViewModel, ViewModel() {
    override val searchFlow = MutableStateFlow<List<ArticleEntity>>(emptyList())
    override val bookmarks = MutableStateFlow<List<ArticleEntity>>(emptyList())

    init {
        viewModelScope.launch(IO) {
            bookmarksUC.bookmarks().collectLatest {
                searchFlow.emit(it)
            }
        }
    }

    override fun searchBookmark(search: String) {
        viewModelScope.launch(IO) {
            searchBookmarksUC.searchBookmark(search).collectLatest {
                searchFlow.emit(it)
            }
        }
    }

}