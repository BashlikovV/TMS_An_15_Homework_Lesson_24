package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository

import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.CurrentUserDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.HistoryDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.UsersDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.mapper.HistoryEntityMapper
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IHistoryRepository

class HistoryRepository(
    private val historyDao: HistoryDao,
    private val currentUserDao: CurrentUserDao,
    private val userDao: UsersDao
) : IHistoryRepository {
    override suspend fun insertNote(itemCommon: ItemCommon) {
        val currentUserEmail = currentUserDao.getCurrentUser().email
        val currentUserId = userDao.getUserByEmail(currentUserEmail)?.id

        currentUserId ?: return
        historyDao.insertHistory(HistoryEntityMapper(currentUserId).mapToEntity(itemCommon))
    }

    override suspend fun getNotes(): List<ItemCommon> {
        val currentUserEmail = currentUserDao.getCurrentUser().email
        val currentUserId = userDao.getUserByEmail(currentUserEmail)?.id
        val historyEntityMapper = HistoryEntityMapper()

        currentUserId ?: throw IllegalStateException()
        return historyDao.getHistoryById(currentUserId).map {
            historyEntityMapper.mapFromEntity(it)
        }
    }
}