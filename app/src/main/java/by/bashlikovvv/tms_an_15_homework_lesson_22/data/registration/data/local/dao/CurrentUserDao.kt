package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.CurrentUserEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract.CurrentUserTable

@Dao
interface CurrentUserDao {

    @Insert(entity = CurrentUserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentUser(currentUser: CurrentUserEntity)

    @Update(entity = CurrentUserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrentUser(currentUser: CurrentUserEntity)

    @Query(
        "SELECT * " +
        "FROM ${CurrentUserTable.TABLE_NAME} " +
        "WHERE ${CurrentUserTable.COLUMN_ID} = 0"
    )
    suspend fun getCurrentUser(): CurrentUserEntity

}