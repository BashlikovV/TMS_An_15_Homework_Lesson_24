package by.bashlikovvv.tms_an_15_homework_lesson_22.features

import android.app.Application
import androidx.room.Room
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.RegistrationDatabase
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.CurrentUserDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.UsersDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository.RegistrationRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository

class App : Application() {

    private val registrationDatabase: RegistrationDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Room.databaseBuilder(
            this, RegistrationDatabase::class.java, RegistrationRoomContract.DATABASE_NAME
        ).build()
    }

    private val usersDao: UsersDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        registrationDatabase.usersDao
    }

    private val currentUserDao: CurrentUserDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        registrationDatabase.currentUserDao
    }

    val registrationRepository: IRegistrationRepository by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RegistrationRepository(usersDao, currentUserDao)
    }
}