package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentNotesItemBasicBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BasicNoteHolder(private val basicNoteItemBinding: FragmentNotesItemBasicBinding)
    : RecyclerView.ViewHolder(basicNoteItemBinding.root) {

    fun bind(item: ItemCommon.NoteItem, onClickListener: (ItemCommon) -> Unit) {
        val formatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = item.time
        basicNoteItemBinding.text = item.text
        basicNoteItemBinding.headerText = item.header
        basicNoteItemBinding.timeText = formatter.format(calendar.time)

        with(basicNoteItemBinding) {
            root.setOnClickListener { onClickListener(item) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): BasicNoteHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return BasicNoteHolder(
                DataBindingUtil
                    .inflate(layoutInflater, R.layout.fragment_notes_item_basic, parent, false)
            )
        }
    }

}