package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model.DetailsFragmentState

class DetailsFragmentViewModel : ViewModel() {

    private var _state = MutableLiveData<DetailsFragmentState>()
    val state = _state

    fun setValues(
        headerText: String,
        timeText: String,
        text: String
    ) {
        _state.postValue(
            DetailsFragmentState(
                headerText,
                timeText,
                text
            )
        )
    }

}