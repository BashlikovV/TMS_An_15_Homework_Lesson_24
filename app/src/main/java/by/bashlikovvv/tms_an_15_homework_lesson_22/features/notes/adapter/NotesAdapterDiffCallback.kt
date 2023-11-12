package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.adapter

import androidx.recyclerview.widget.DiffUtil
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon

class NotesAdapterDiffCallback : DiffUtil.ItemCallback<ItemCommon>() {
    override fun areItemsTheSame(oldItem: ItemCommon, newItem: ItemCommon): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ItemCommon, newItem: ItemCommon): Boolean {
        return oldItem == newItem
    }
}