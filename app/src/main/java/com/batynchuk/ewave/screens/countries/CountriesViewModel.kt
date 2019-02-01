package com.batynchuk.ewave.screens.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.batynchuk.ewave.common.Workers
import com.batynchuk.ewave.common.base.BaseViewModel
import com.batynchuk.ewave.data.country.CountryDto
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class CountriesViewModel(
    private val useCase: CountriesListUseCase,
    private val router: CountriesRouter,
    private val workers: Workers
) : BaseViewModel() {

    val countriesList = MutableLiveData<List<CountryDto?>?>()

    fun loadAllCountries() {
        if (countriesList.value == null) {
            isLoadingLiveData.value = true
            launch(workers.io) {
                try {
                    val allCountries = useCase.getAllCountries().await()
                    withContext(workers.main) {
                        countriesList.value = allCountries
                        isLoadingLiveData.value = false
                    }
                } catch (exception: Exception) {
                    postException(exception)
                    isLoadingLiveData.postValue(false)
                }

            }
        }
    }

    fun onCountryClick(position: Int) {
        countriesList.value?.get(position)?.let {
            router.showNeighbours(it.name!!)
        }
    }
}

class CountriesViewModelFactory @Inject constructor(
    private val useCase: CountriesListUseCase,
    private val router: CountriesRouter,
    private val workers: Workers
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountriesViewModel(useCase, router, workers) as T
        } else throw IllegalStateException(
            "Unexpected view model class. " +
                    "Expected: ${CountriesViewModel::class.java.simpleName}, " +
                    "found: ${modelClass.simpleName}"
        )
    }

}