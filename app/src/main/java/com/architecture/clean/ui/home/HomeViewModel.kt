package com.architecture.clean.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.clean.domain.model.FoodDto
import com.architecture.clean.domain.model.response.ErrorModel
import com.architecture.clean.domain.model.response.ErrorStatus
import com.architecture.clean.domain.usecase.GetAllFoodsUseCase
import com.architecture.clean.domain.usecase.GetHomeUseCase
import com.architecture.clean.domain.usecase.InsertFoodsUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val insertFoodsUseCase: InsertFoodsUseCase,
    private val getAllFoodsUseCase: GetAllFoodsUseCase
) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName
    val homeData: MutableLiveData<FoodDto> by lazy { MutableLiveData<FoodDto>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val foodsCount: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    init {

        getHomeUseCase.execute {
            onComplete {
                Log.d(TAG, it.toString())
                homeData.value = it
                insert(it)
            }

            onError { throwable ->
                if (throwable.errorStatus == ErrorStatus.UNAUTHORIZED) {
                    doReshresh()
                } else {
                    error.value = throwable
                }

            }

            onCancel {

            }
        }
    }

    private fun doReshresh() {

    }


    fun insert(foodDto: FoodDto) {
        insertFoodsUseCase.foodDto = foodDto
        insertFoodsUseCase.execute {

            onComplete {
                returnFoodsInDb()
            }

            onError { throwable ->
                error.value = throwable
            }

            onCancel {

            }
        }
    }

    private fun returnFoodsInDb() {
        getAllFoodsUseCase.execute {
            onComplete {
                foodsCount.value = it.size
            }
            onError {
                error.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        insertFoodsUseCase.unsubscribe()
        getHomeUseCase.unsubscribe()
        getAllFoodsUseCase.unsubscribe()
    }
}