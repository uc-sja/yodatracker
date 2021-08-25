package com.test.locationupdates.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.locationupdates.model.repository.LocationRepo

class LocationViewModelFactory(private val repo: LocationRepo) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationViewModel(repo) as T
    }
}