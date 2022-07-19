package xyz.zohre.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurants(
    val name: String,
    val status: String,
    val sortingValues: SortingValues
): Parcelable