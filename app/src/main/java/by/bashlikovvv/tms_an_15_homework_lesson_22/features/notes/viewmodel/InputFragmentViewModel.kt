package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model.InputFragmentState

class InputFragmentViewModel : ViewModel() {

    private var _state = MutableLiveData<InputFragmentState>()
    val state = _state

    fun setNote(note: ItemCommon) {
        _state.postValue(InputFragmentState(note))
    }

}