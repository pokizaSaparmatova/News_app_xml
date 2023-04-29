package uz.gita.newsappxml.data.remote.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

//import kotlinx.android.parcel.Parcelize


@Parcelize
data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    @Embedded val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) : Parcelable