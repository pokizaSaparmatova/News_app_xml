package uz.gita.newsappxml.domain.useCase.impl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.LatestNews
import javax.inject.Inject

class LatestNewsImpl @Inject constructor(
    private val repo: NewsRepository
) : LatestNews {
    override suspend fun latestNews(): Flow<PagingData<Article>> {
        return repo.latestNews()
    }
}