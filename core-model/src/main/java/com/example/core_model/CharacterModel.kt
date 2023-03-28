package com.example.core_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: Int? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,

    ) : Parcelable
