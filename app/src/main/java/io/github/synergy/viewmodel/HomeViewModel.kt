package io.github.synergy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.github.synergy.controller.InfoController
import io.github.synergy.dto.Coordinates
import io.github.synergy.dto.EmergencyContact
import kotlinx.coroutines.launch

class HomeViewModel(private val infoController: InfoController): ViewModel() {
    private val _locationScore: MutableLiveData<Double> = MutableLiveData(0.00)
    val locationScore: LiveData<Double> = _locationScore
    private val _coordinates: MutableLiveData<Coordinates> = MutableLiveData()
    val coordinates: LiveData<Coordinates> = _coordinates

    suspend fun fetchLocationScore(latitude: String, longitude: String) {
        _coordinates.postValue(Coordinates(latitude, longitude))
        val score = infoController.fetchLocationSafetyIndex(latitude, longitude)
        _locationScore.postValue(score)
    }

    class Factory(private val infoController: InfoController): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(infoController) as T
            }
            return super.create(modelClass)
        }
    }
}