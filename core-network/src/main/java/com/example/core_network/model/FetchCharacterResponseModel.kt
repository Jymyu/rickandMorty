package com.example.core_network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FetchCharacterResponseModel(
    @SerializedName("info")
    @Expose
    val infoModel: FetchCharacterInfoModel? = null,
    val results: List<FetchCharacterResultModel>? = null

)