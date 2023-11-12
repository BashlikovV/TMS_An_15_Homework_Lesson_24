package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentNotesItemImportantBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ImportantNoteHolder(private val importantNoteItemBinding: FragmentNotesItemImportantBinding)
    : RecyclerView.ViewHolder(importantNoteItemBinding.root) {

    fun bind(item: ItemCommon.ErrorItem, onClickListener: (ItemCommon) -> Unit) {
        val formatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = item.time
        importantNoteItemBinding.text = item.text
        importantNoteItemBinding.headerText = item.header
        importantNoteItemBinding.timeText = formatter.format(calendar.time)

        with(importantNoteItemBinding) {
            root.setOnClickListener { onClickListener(item) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ImportantNoteHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return ImportantNoteHolder(
                FragmentNotesItemImportantBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

}