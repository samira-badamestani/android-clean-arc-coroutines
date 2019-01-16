package com.architecture.clean.domain.repository

import com.architecture.clean.domain.model.Food
import com.architecture.clean.domain.model.FoodDto

interface AppRepository{
    suspend fun getHome(): FoodDto
    suspend fun saveFoods(foodDto: FoodDto): Long
    suspend fun selectAllFoods() : MutableList<Food>
}