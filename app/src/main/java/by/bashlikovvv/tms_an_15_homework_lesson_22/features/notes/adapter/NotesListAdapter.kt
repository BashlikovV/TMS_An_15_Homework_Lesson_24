package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon

class NotesListAdapter(
    private val onClickListener: (ItemCommon) -> Unit
) : ListAdapter<ItemCommon, RecyclerView.ViewHolder>(NotesAdapterDiffCallback()) {
  
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
          VIEW_TYPE_BASIC -> BasicNoteHolder.from(parent)
          else -> ImportantNoteHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is BasicNoteHolder && item is ItemCommon.NoteItem) {
            holder.bind(item, onClickListener)
        } else if (holder is ImportantNoteHolder && item is ItemCommon.ErrorItem) {
            holder.bind(item, onClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ItemCommon.NoteItem -> VIEW_TYPE_BASIC
            else -> VIEW_TYPE_IMPORTANT
        }
    }

    companion object {
        const val VIEW_TYPE_BASIC = 0
        const val VIEW_TYPE_IMPORTANT = 1
    }

}
