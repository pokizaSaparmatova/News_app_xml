package uz.gita.newsappxml.data.remote.model

data class BaseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)