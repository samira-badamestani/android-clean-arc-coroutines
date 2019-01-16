package com.architecture.clean.domain.model

import com.google.gson.annotations.SerializedName

data class FoodDto(
        @SerializedName("results") val results: ArrayList<Food>
)