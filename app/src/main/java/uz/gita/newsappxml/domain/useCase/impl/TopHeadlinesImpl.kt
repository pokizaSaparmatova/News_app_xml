package uz.gita.newsappxml.domain.useCase.impl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.TopHeadlines
import javax.inject.Inject

class TopHeadlinesImpl @Inject constructor(
    private val repo: NewsRepository
): TopHeadlines {
    override suspend fun topHeadlines(): Flow<PagingData<Article>> {
        return repo.topHeadlines()
    }
}