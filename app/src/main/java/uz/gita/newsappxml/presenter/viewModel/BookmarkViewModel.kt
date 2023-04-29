package uz.gita.newsappxml.presenter.viewModel

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

interface BookmarkViewModel {
    val searchFlow: Flow<List<ArticleEntity>>
    val bookmarks:Flow<List<ArticleEntity>>
    fun searchBookmark(search:String)

}