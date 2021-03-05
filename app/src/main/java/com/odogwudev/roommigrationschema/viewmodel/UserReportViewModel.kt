package com.odogwudev.roommigrationschema.viewmodel

import androidx.lifecycle.*
import com.odogwudev.roommigrationschema.datamodel.UserReportCardEntity
import com.odogwudev.roommigrationschema.repository.UserRepository
import kotlinx.coroutines.launch

class UserReportViewModel(private val repository: UserRepository) : ViewModel() {

    val allUserReportList: LiveData<List<UserReportCardEntity>> =
        repository.allUserReportList.asLiveData()

    fun insertUserReport(userReportCardEntity: UserReportCardEntity) = viewModelScope.launch {
        repository.insertUserReport(userReportCardEntity)
    }

    fun deleteUserReport(userReportCardEntity: UserReportCardEntity) = viewModelScope.launch {
        repository.deleteUserReport(userReportCardEntity)
    }

    fun updateUserReport(userReportCardEntity: UserReportCardEntity) = viewModelScope.launch {
        repository.updateUserReport(userReportCardEntity)
    }

    fun deleteAllUserReport() = viewModelScope.launch {
        repository.deleteAllUserReportList()
    }

    class userViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserReportViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserReportViewModel(repository) as T
            }
            throw IllegalStateException("Unknown ViewMOdel Class")
        }
    }
}