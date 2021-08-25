package ru.fishteck.movies_time.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.fishteck.movies_time.data.models.ProfileModel
import ru.fishteck.movies_time.data.repository.ProfileRepository
import ru.fishteck.movies_time.utils.states.ProfileState
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profileState = MutableLiveData<ProfileState<ProfileModel>>()
    val profileState: LiveData<ProfileState<ProfileModel>> = _profileState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _profileState.postValue(ProfileState.Error(throwable.javaClass.simpleName))
    }

    fun loadProfile(id: Int) {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                _profileState.postValue(ProfileState.Success(profileRepository.loadProfile(id)))
            }
        }
    }

    private fun updateProfile(profile: ProfileModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                profileRepository.updateProfile(profile)
            }
        }
    }

    fun addProfile(profile: ProfileModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                profileRepository.addProfile(profile)
            }
        }
    }

     fun deleteProfileById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                profileRepository.deleteProfileById(id)
            }
        }
    }

}