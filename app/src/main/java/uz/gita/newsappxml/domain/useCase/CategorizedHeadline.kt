package uz.gita.newsappxml.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article

interface CategorizedHeadline {
    suspend fun categorizedHeadlines(category: String): Flow<List<Article>>
}