package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.HistoryEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract.HistoryTable

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Query(
        "SELECT *" +
        "FROM ${HistoryTable.TABLE_NAME} " +
        "WHERE ${HistoryTable.COLUMN_USER_ID} = :id"
    )
    suspend fun getHistoryById(id: Long): List<HistoryEntity>
}