package uz.gita.newsappxml.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

@Dao
interface NewsDao {
    @Insert
    suspend fun insert(articleEntity: ArticleEntity)

    @Delete
    suspend fun delete(articleEntity: ArticleEntity)

    @Query("SELECT * FROM article WHERE title=:title")
    suspend fun check(title:String?):ArticleEntity?

    @Query("SELECT * FROM article")
    fun bookmarks(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM article WHERE title LIKE '%'|| :search ||'%'")
    fun searchBookmark(search: String): Flow<List<ArticleEntity>>
}