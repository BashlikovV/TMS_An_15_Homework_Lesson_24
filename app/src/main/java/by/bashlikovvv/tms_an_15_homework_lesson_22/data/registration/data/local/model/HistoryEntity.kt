package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract.HistoryTable

@Entity(tableName = HistoryTable.TABLE_NAME)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HistoryTable.COLUMN_ID) val id: Long,
    @ColumnInfo(name = HistoryTable.COLUMN_BODY) val body: String,
    @ColumnInfo(name = HistoryTable.COLUMN_HEADER) val header: String,
    @ColumnInfo(name = HistoryTable.COLUMN_TIME) val time: Long,
    @ColumnInfo(name = HistoryTable.COLUMN_ERROR) val error: Boolean,
    @ColumnInfo(name = HistoryTable.COLUMN_USER_ID) val userId: Long = 0
)