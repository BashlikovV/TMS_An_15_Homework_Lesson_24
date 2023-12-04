package by.bashlikovvv.tms_an_15_homework_lesson_22.data.di

import android.content.Context
import androidx.room.Room
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.notes.ApplicationData
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.RegistrationDatabase
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract.RegistrationRoomContract
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.CurrentUserDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.HistoryDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.UsersDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository.HistoryRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository.RegistrationRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IHistoryRepository
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRegistrationDatabase(
        @ApplicationContext ctx: Context
    ): RegistrationDatabase {
        return Room.databaseBuilder(
            ctx,
            RegistrationDatabase::class.java,
            RegistrationRoomContract.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(
        db: RegistrationDatabase
    ): UsersDao {
        return db.usersDao
    }

    @Provides
    @Singleton
    fun provideCurrentUserDao(
        db: RegistrationDatabase
    ): CurrentUserDao {
        return db.currentUserDao
    }

    @Provides
    @Singleton
    fun provideHistoryDao(
        db: RegistrationDatabase
    ): HistoryDao {
        return db.historyDao
    }

    @Provides
    fun provideRegistrationRepository(
        usersDao: UsersDao,
        currentUserDao: CurrentUserDao
    ): IRegistrationRepository {
        return RegistrationRepository(usersDao, currentUserDao)
    }

    @Provides
    fun provideHistoryRepository(
        historyDao: HistoryDao,
        currentUserDao: CurrentUserDao,
        usersDao: UsersDao
    ): IHistoryRepository {
        return HistoryRepository(historyDao, currentUserDao, usersDao)
    }

}