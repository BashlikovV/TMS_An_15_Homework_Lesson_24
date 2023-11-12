package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract.CurrentUserTable

@Entity(tableName = CurrentUserTable.TABLE_NAME)
data class CurrentUserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = CurrentUserTable.COLUMN_ID) val id: Long,
    @ColumnInfo(name = CurrentUserTable.COLUMN_CURRENT_USER_EMAIL) val email: String,
    @ColumnInfo(name = CurrentUserTable.COLUMN_SIGNED_IN) val signedIn: Boolean
)