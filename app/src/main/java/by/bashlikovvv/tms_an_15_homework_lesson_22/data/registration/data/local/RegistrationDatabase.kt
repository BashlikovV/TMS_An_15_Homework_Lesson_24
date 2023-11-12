package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.CurrentUserDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.UsersDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.CurrentUserEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.UserEntity

@Database(
    entities = [UserEntity::class, CurrentUserEntity::class],
    version = 1
)
abstract class RegistrationDatabase : RoomDatabase() {
    abstract val usersDao: UsersDao
    abstract val currentUserDao: CurrentUserDao
}