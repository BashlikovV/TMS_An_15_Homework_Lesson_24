package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.notes.ApplicationData
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model.NotesFragmentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesFragmentViewModel(
    private val registrationRepository: IRegistrationRepository,
    private val data: ApplicationData
) : ViewModel() {

    private var _state = MutableStateFlow(NotesFragmentState())
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(notes = data.notes) }
    }

    fun logOut() = viewModelScope.launch {
        registrationRepository.logOut()
    }

    fun openNote(note: ItemCommon) {
        _state.update { it.copy(
            onNoteClicked = true,
            note = note
        ) }
        _state.update { it.copy(
            onNoteClicked = false
        ) }
    }

    fun addNote() {
        _state.update { it.copy(onAddNoteClicked = true) }
        _state.update { it.copy(onAddNoteClicked = false) }
    }

    class Factory (
        private val registrationRepository: IRegistrationRepository,
        private val data: ApplicationData
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == NotesFragmentViewModel::class.java)
            return NotesFragmentViewModel(registrationRepository, data) as T
        }
    }

}