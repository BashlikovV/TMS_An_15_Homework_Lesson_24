package by.bashlikovvv.tms_an_15_homework_lesson_22.data.notes

import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object ApplicationData {

    val notes = mutableListOf<ItemCommon>()

    private var currentNoteIdx = 0

    init {
        notes.addAll((0..10).map {
            val header = "header$it"
            val text = "text$it"
            val time = Calendar.getInstance().time.time
            listOf(
                ItemCommon.ErrorItem(
                    header, text, time
                ),
                ItemCommon.NoteItem(
                    header, text, time
                )
            ).random()
        })
    }

    fun addNote(item: ItemCommon) {
        notes.add(item)
    }

    private fun getCurrentNote() = notes[currentNoteIdx]

    fun setCurrentNoteIdx(item: ItemCommon) {
        currentNoteIdx = notes.indexOf(item)
    }

    fun getNoteData(currentNote: ItemCommon = getCurrentNote()): Triple<String, String, String> {
        val formatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        val calendar = Calendar.getInstance()
        var header = ""
        var text = ""
        var time = ""
        when (currentNote) {
            is ItemCommon.NoteItem -> {
                header = currentNote.header
                text = currentNote.text
                calendar.timeInMillis = currentNote.time
                time = formatter.format(calendar.time)
            }
            is ItemCommon.ErrorItem -> {
                header = currentNote.header
                text = currentNote.text
                calendar.timeInMillis = currentNote.time
                time = formatter.format(calendar.time)
            }
        }
        return Triple(header, text, time)
    }
}