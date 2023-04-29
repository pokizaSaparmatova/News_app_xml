package uz.gita.newsappxml.presenter.viewModel

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

interface ArticleViewModel {
    val isBookmarked: Flow<Boolean>

    fun bookmark(articleEntity: ArticleEntity)
    fun unBookmark(articleEntity: ArticleEntity)
    suspend fun check(title: String): Boolean
}