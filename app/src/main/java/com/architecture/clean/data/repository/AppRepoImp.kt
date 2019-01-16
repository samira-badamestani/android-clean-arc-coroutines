package com.architecture.clean.data.repository

import com.architecture.clean.data.source.cloud.BaseCloudRepository
import com.architecture.clean.data.source.db.FoodDao
import com.architecture.clean.domain.model.Food
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.repository.AppRepository
import javax.inject.Inject

class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository,
    private val foodDao: FoodDao
) : AppRepository {
    override suspend fun selectAllFoods(): MutableList<Food> {

        return foodDao.selectAllFoods()

    }

    override suspend fun saveFoods(foodDto: FoodDto): Long {
        if (foodDto.results.size > 0) {
            for (food in foodDto.results) {
                foodDao.insertFood(food)
            }

        }
        return 0L
    }

    override suspend fun getHome(): FoodDto {
        return cloudRepository
            .getHome()
    }


}