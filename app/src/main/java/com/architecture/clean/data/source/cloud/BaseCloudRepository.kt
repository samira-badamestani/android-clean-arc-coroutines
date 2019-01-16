package com.architecture.clean.data.source.cloud

import com.architecture.clean.domain.model.FoodDto

interface BaseCloudRepository {
   suspend fun getHome(): FoodDto
}