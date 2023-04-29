package uz.gita.newsappxml.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity


interface Bookmarks {
    suspend fun bookmarks():Flow<List<ArticleEntity>>
}