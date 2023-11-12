package by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.RegistrationFailedException
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.User
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.domain.model.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpFragmentViewModel(
    private val registrationRepository: IRegistrationRepository
) : ViewModel() {

    private var _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        try {
            registrationRepository.signUp(User(email, password))
            _state.update { it.copy(success = true, exception = "") }
        } catch (_: RegistrationFailedException.AccountAlreadyExistsException) {
            _state.update { it.copy(exception = "AccountAlreadyExistsException", success = false) }
        }
    }

    class Factory (
        private val registrationRepository: IRegistrationRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == SignUpFragmentViewModel::class.java)
            return SignUpFragmentViewModel(registrationRepository) as T
        }
    }

}