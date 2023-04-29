package uz.gita.newsappxml.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

interface SearchBookmarks {
    suspend fun searchBookmark(search:String):Flow<List<ArticleEntity>>
}