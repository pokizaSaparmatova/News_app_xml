package uz.gita.newsappxml.data.remote.model

import android.os.Parcelable
//import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parcelize


@Parcelize
data class Source(
    val id: String?,
    val name: String
):Parcelable