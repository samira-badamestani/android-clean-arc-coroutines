package com.architecture.clean.data.source.cloud

import com.architecture.clean.data.restful.ApiService
import com.architecture.clean.domain.model.FoodDto

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getHome(): FoodDto {
        return apIs.getHome().await()
    }
}
