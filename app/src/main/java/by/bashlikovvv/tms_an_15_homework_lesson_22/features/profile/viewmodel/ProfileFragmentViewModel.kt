package by.bashlikovvv.tms_an_15_homework_lesson_22.features.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.CurrentUserInfo
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
    private val registrationRepository: IRegistrationRepository
) : ViewModel() {

    private var _state = MutableLiveData(CurrentUserInfo())
    val state: LiveData<CurrentUserInfo> = _state

    fun initState() = viewModelScope.launch {
        _state.postValue(registrationRepository.getCurrentUserInfo())
    }

}