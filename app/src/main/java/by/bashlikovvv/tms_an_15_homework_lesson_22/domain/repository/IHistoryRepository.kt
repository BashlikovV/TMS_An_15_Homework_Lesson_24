package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository

import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon

interface IHistoryRepository {

    suspend fun insertNote(itemCommon: ItemCommon)

    suspend fun getNotes(): List<ItemCommon>

}