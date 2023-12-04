package by.bashlikovvv.tms_an_15_homework_lesson_22.features.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ApplicationState
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val registrationRepository: IRegistrationRepository
) : ViewModel() {

    private var _state = MutableStateFlow(ApplicationState())
    val state = _state.asStateFlow()

    init {
        isSignedIn()
    }

    private fun isSignedIn() {
        viewModelScope.launch {
            _state.update {
                it.copy(isSignedIn = registrationRepository.isSignedIn())
            }
        }
    }

}