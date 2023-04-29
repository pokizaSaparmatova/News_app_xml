package uz.gita.newsappxml.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.Bookmarks
import javax.inject.Inject

class BookmarksImpl @Inject constructor(
    private val repo:NewsRepository
): Bookmarks {
    override suspend fun bookmarks(): Flow<List<ArticleEntity>> {
        return repo.bookmarks()
    }
}