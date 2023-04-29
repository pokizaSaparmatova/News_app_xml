package uz.gita.newsappxml.domain.useCase

import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

interface BookmarkOperations {
    suspend fun bookmark(articleEntity: ArticleEntity)
    suspend fun unBookmark(articleEntity: ArticleEntity)
}