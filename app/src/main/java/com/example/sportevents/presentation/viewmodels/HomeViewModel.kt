package com.example.sportevents.presentation.viewmodels

import androidx.lifecycle.*
import com.example.sportevents.data.model.Result
import com.example.sportevents.domain.FixtureRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FixtureRepositoryImpl): ViewModel() {

    private val _fixtureModelData = MutableLiveData<List<Result>>()
    val fixtureModelLiveData: LiveData<List<Result>> get() = _fixtureModelData

    private val currentDateRange = MutableLiveData<Pair<String?, String?>>()

    private val defaultDateFrom = "2022-01-01"
    private val defaultDateTo = "2022-01-10"

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _position = MutableLiveData<Int>()
    val position:LiveData<Int> get() = _position

    private fun getData() {

        _isLoading.value = true

        viewModelScope.launch {
            _fixtureModelData.value = repository.getData(
                currentDateRange.value?.first ?: defaultDateFrom,
                currentDateRange.value?.second ?: defaultDateTo
            ).result
            _isLoading.value = false
        }
    }

    fun setDate(datePair: Pair<String?, String?>) {
        currentDateRange.value = datePair
        getData()
    }

    fun setPosition(position: Int) {
        _position.value = position
    }

    init {
        currentDateRange.value = Pair(defaultDateFrom, defaultDateTo)
        getData()
    }
}

class HomeViewModelFactory(private val repository: FixtureRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}