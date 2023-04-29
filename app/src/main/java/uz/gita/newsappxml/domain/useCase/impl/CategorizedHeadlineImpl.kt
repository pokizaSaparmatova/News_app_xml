package uz.gita.newsappxml.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.CategorizedHeadline
import javax.inject.Inject

class CategorizedHeadlineImpl @Inject constructor(
    private val repo:NewsRepository
): CategorizedHeadline {
    override suspend fun categorizedHeadlines(category: String): Flow<List<Article>> {
        return repo.categorizedHeadlines(category)
    }
}