package uz.gita.newsappxml.domain.useCase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.remote.model.Article

interface LatestNews {
    suspend fun latestNews(): Flow<PagingData<Article>>
}