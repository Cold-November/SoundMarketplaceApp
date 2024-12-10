package com.example.soundmarketplaceapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SoundData(
    val systemName: String,
    val title: String,
    val tags: String,
    val price: Double,
    val license: String,
    val audioUri: String
) : Parcelable
