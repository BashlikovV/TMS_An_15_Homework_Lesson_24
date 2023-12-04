package by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.RegistrationFailedException
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.User
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.domain.model.SignInState
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.domain.model.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInFragmentViewModel @Inject constructor(
    private val registrationRepository: IRegistrationRepository
) : ViewModel() {

    private var _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        try {
            registrationRepository.signIn(User(email, password), true)
            _state.update { it.copy(success = true, exception = "") }
        } catch (_: RegistrationFailedException.IncorrectPasswordException) {
            _state.update { it.copy(success = false, exception = "Incorrect password") }
        } catch (_: RegistrationFailedException.AccountNotFoundException) {
            _state.update { it.copy(success = false, exception = "Account not found") }
        }
    }

    class Factory (
        private val registrationRepository: IRegistrationRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == SignInFragmentViewModel::class.java)
            return SignInFragmentViewModel(registrationRepository) as T
        }
    }

}