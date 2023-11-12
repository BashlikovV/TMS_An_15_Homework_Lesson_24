package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ItemCommon: Parcelable {

    @Parcelize
    data class NoteItem(
        val header: String = "",
        val text: String = "",
        val time: Long = 0L
    ) : ItemCommon()

    @Parcelize
    data class ErrorItem(
        val header: String = "",
        val text: String = "",
        val time: Long = 0L
    ) : ItemCommon()

}