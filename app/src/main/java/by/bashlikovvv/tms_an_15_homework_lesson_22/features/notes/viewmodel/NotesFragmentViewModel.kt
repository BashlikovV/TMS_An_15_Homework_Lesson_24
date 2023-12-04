package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.notes.ApplicationData
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository.HistoryRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IHistoryRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model.LocalChanges
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model.NotesFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class NotesFragmentViewModel @Inject constructor(
    private val registrationRepository: IRegistrationRepository,
    private val historyRepository: IHistoryRepository
) : ViewModel() {

    var notes: Flow<List<ItemCommon>> = flow {
        emit(historyRepository.getNotes())
    }
        private set

    private val _searchBy: MutableStateFlow<String> = MutableStateFlow("")

    private var _state = MutableStateFlow(NotesFragmentState())
    val state = _state.asStateFlow()

    private val localChanges = LocalChanges()
    private val localChangesFlow = MutableStateFlow(OnChange(localChanges))

    init {
        val originPokemonFlow = _searchBy.asStateFlow()
            .debounce(500)
            .flatMapLatest {
                if (it.isEmpty()) {
                    flowOf(historyRepository.getNotes())
                } else {
                    val notes = mutableListOf<ItemCommon>()
                    historyRepository.getNotes().forEach { item ->
                        when(item) {
                            is ItemCommon.NoteItem -> {
                                if (item.header.contains(it)) {
                                    notes.add(item)
                                }
                            }
                            is ItemCommon.ErrorItem -> {
                                if (item.header.contains(it)) {
                                    notes.add(item)
                                }
                            }
                        }
                    }
                    flowOf(notes)
                }
            }

        notes = combine(
            originPokemonFlow,
            localChangesFlow,
            this::merge
        )
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

    fun onSearchTextChange(value: String) = _searchBy.update { value }

    private fun merge(notes: List<ItemCommon>, localChanges: OnChange<LocalChanges>): List<ItemCommon> {
        return notes
    }

    @JvmInline
    value class OnChange<T>(val value: T)

}