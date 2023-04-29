package uz.gita.newsappxml.domain.useCase.impl

import uz.gita.newsappxml.data.local.room.entity.ArticleEntity
import uz.gita.newsappxml.data.repository.NewsRepository
import uz.gita.newsappxml.domain.useCase.Check
import javax.inject.Inject

class CheckImpl @Inject constructor(
    private val repo: NewsRepository
) : Check {
    override suspend fun check(title: String?): ArticleEntity? {
        return repo.check(title)
    }
}