package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Destination : Parcelable {

    @Parcelize
    data object Notes : Destination()

    @Parcelize
    data object Registration : Destination()

    @Parcelize
    data object AddNote : Destination()

    @Parcelize
    data class ReadNote(val note: ItemCommon) : Destination()
}