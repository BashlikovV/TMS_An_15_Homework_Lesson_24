package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model

import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon

data class NotesFragmentState(
    val onNoteClicked: Boolean = false,
    val onAddNoteClicked: Boolean = false,
    val note: ItemCommon = ItemCommon.NoteItem()
)