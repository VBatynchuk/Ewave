package com.batynchuk.ewave.screens.neighbours

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.batynchuk.ewave.common.Workers
import com.batynchuk.ewave.common.base.BaseViewModel
import com.batynchuk.ewave.data.country.CountryDto
import com.batynchuk.ewave.data.country.CountryNeighbourDto
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class NeighbourViewModel(
    private val useCase: NeighbourUseCase,
    private val router: NeighbourRouter,
    private val workers: Workers
) : BaseViewModel() {

    val neighboursList = MutableLiveData<List<CountryDto?>>()

    fun loadCountryNeighbours(countryName: String?) {
        isLoadingLiveData.value = true
        launch(workers.io) {
            if (countryName != null) {
                try {
                    val neighbourCountries = useCase.getCountryNeighbours(countryName).await()[0]
                    val allCountries = useCase.getAllCountries().await()
                    val result = ArrayList<CountryDto?>()
                    neighbourCountries.borders?.forEach { border ->
                        result.add(allCountries.find { border == it.alpha3Code })
                    }
                    withContext(workers.main) {
                        isLoadingLiveData.value = false
                        neighboursList.value = result
                    }
                } catch (exception: Exception) {
                    postException(exception)
                    isLoadingLiveData.postValue(false)
                }
            }
        }
    }
}

class NeighboursViewModelFactory @Inject constructor(
    private val useCase: NeighbourUseCase,
    private val router: NeighbourRouter,
    private val workers: Workers
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NeighbourViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NeighbourViewModel(useCase, router, workers) as T
        } else throw IllegalStateException(
            "Unexpected view model class. " +
                    "Expected: ${NeighbourViewModel::class.java.simpleName}, " +
                    "found: ${modelClass.simpleName}"
        )
    }

}