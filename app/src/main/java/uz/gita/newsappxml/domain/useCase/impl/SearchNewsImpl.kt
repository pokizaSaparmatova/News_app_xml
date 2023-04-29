package uz.gita.newsappxml.domain.useCase.impl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.SearchNews
import javax.inject.Inject

class SearchNewsImpl @Inject constructor(
    private val repo:NewsRepository
): SearchNews {
    override suspend fun search(search: String): Flow<PagingData<Article>> {
        return repo.search(search)
    }
}