package com.architecture.clean.domain.usecase

import com.architecture.clean.data.mapper.CloudErrorMapper
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.repository.AppRepository
import com.architecture.clean.domain.usecase.base.UseCase
import javax.inject.Inject

class InsertFoodsUseCase @Inject constructor(
        errorUtil: CloudErrorMapper,
        private val appRepository: AppRepository
) : UseCase<Long>(errorUtil) {
    var foodDto=FoodDto(arrayListOf())
    override suspend fun executeOnBackground(): Long {
        return appRepository.saveFoods(foodDto)
    }

}