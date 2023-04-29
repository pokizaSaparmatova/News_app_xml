package uz.gita.newsappxml.presenter.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.domain.useCase.BookmarkOperations
import uz.gita.newsappxml.domain.useCase.Check
import uz.gita.newsappxml.presenter.viewModel.ArticleViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModelImpl @Inject constructor(
    private val checkUC: Check,
    private val bookmarkOperationsUC: BookmarkOperations
) : ArticleViewModel, ViewModel() {
    override val isBookmarked =
        MutableSharedFlow<Boolean>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    init {
        //safe args dan title ni olib check ga berib yubor...`
    }

    override fun bookmark(articleEntity: ArticleEntity) {
        viewModelScope.launch(IO) {
            bookmarkOperationsUC.bookmark(articleEntity)
        }
    }

    override fun unBookmark(articleEntity: ArticleEntity) {
        viewModelScope.launch(IO) {
            bookmarkOperationsUC.unBookmark(articleEntity)
        }
    }

    override suspend fun check(title: String): Boolean {
        val bookmark: ArticleEntity? = checkUC.check(title)
        return bookmark != null
//        viewModelScope.launch(IO) {
//            val bookmark:ArticleEntity? = checkUC.check(title)
//
//            if (bookmark == null) {
//                isBookmarked.emit(false)
//            } else {
//                isBookmarked.emit(true)
//            }
//        }
    }
}