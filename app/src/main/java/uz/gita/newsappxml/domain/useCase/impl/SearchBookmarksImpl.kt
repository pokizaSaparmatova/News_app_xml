package uz.gita.newsappxml.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.SearchBookmarks
import javax.inject.Inject

class SearchBookmarksImpl @Inject constructor(
    private val repo:NewsRepository
) : SearchBookmarks {
    override suspend fun searchBookmark(search: String): Flow<List<ArticleEntity>> {
        return repo.searchBookmarks(search)
    }
}