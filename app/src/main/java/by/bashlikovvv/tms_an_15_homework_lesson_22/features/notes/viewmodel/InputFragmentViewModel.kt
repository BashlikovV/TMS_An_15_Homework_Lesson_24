package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository.HistoryRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IHistoryRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model.InputFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputFragmentViewModel @Inject constructor(
    private val historyRepository: IHistoryRepository
) : ViewModel() {

    private var _state = MutableLiveData<InputFragmentState>()
    val state = _state

    fun setNote(note: ItemCommon) {
        _state.postValue(InputFragmentState(note))
    }

    fun addNote(item: ItemCommon) = viewModelScope.launch {
        historyRepository.insertNote(item)
    }

}