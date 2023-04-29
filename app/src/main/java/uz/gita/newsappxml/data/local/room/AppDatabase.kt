package uz.gita.newsappxml.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.newsappxml.data.local.room.dao.NewsDao
import uz.gita.newsappxml.data.local.room.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun newsDao():NewsDao

}