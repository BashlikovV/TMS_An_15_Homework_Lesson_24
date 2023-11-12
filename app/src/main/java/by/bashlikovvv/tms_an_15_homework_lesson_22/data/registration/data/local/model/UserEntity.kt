package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract.UsersTable

@Entity(tableName = UsersTable.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UsersTable.COLUMN_ID) val id: Long,
    @ColumnInfo(name = UsersTable.COLUMN_EMAIL) val email: String,
    @ColumnInfo(name = UsersTable.COLUMN_HASH) val hash: String,
    @ColumnInfo(name = UsersTable.COLUMN_SALT) val salt: String,
    @ColumnInfo(name = UsersTable.COLUMN_TIME) val time: Long
)