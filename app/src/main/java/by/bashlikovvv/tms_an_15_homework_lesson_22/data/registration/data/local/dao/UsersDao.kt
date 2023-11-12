package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.UserEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract.UsersTable

@Dao
interface UsersDao {

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query(
        "SELECT * " +
        "FROM ${UsersTable.TABLE_NAME} " +
        "WHERE ${UsersTable.COLUMN_EMAIL} = :email"
    )
    suspend fun getUserByEmail(email: String): UserEntity?

}