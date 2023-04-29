package uz.gita.newsappxml.domain.useCase

import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

interface Check {
    suspend fun check(title:String?): ArticleEntity?
}