package uz.gita.newsappxml.domain.useCase.impl

import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.BookmarkOperations
import javax.inject.Inject

class BookmarkOperationsImpl @Inject constructor(
    private val repo:NewsRepository
): BookmarkOperations {
    override suspend fun bookmark(articleEntity: ArticleEntity) {
        repo.bookmarkArticle(articleEntity)
    }

    override suspend fun unBookmark(articleEntity: ArticleEntity) {
        repo.unBookmarkArticle(articleEntity)
    }
}