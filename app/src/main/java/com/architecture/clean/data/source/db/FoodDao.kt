package com.architecture.clean.data.source.db

import androidx.room.*
import com.architecture.clean.domain.model.Food


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food): Long

    @Delete
   suspend fun deleteFood(food: Food): Int

    @Query("SELECT * from Food")
   suspend fun selectAllFoods(): MutableList<Food>

}