package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.repository

import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.CurrentUserDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.dao.UsersDao
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.mapper.CurrentUserEntityMapper
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.mapper.UserEntityMapper
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.util.SecurityUtilsImpl
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.RegistrationFailedException
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.User
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository.IRegistrationRepository

class RegistrationRepository(
    private val usersDao: UsersDao,
    private val currentUserDao: CurrentUserDao
) : IRegistrationRepository {

    private var rememberMe: Boolean? = null

    private val securityUtils = SecurityUtilsImpl()

    @Throws(RegistrationFailedException::class)
    override suspend fun signUp(user: User) {
        val databaseUser = usersDao.getUserByEmail(user.email)
        if (databaseUser == null) {
            usersDao.insertUser(UserEntityMapper().mapFromEntity(user))
        } else {
            throw RegistrationFailedException.AccountAlreadyExistsException
        }
    }

    @Throws(RegistrationFailedException::class)
    override suspend fun signIn(user: User, rememberMe: Boolean) {
        val databaseUser = usersDao.getUserByEmail(user.email)
        databaseUser ?: throw RegistrationFailedException.AccountNotFoundException
        val localSalt = securityUtils.stringToBytes(databaseUser.salt)
        if (user.password.isBlank()) {
            throw RegistrationFailedException.IncorrectPasswordException
        }
        val userPassword = user.password.toCharArray()
        val userHash = securityUtils.passwordToHash(userPassword, localSalt)
        val dbHash = securityUtils.stringToBytes(databaseUser.hash)

        if (!userHash.contentEquals(dbHash)) {
            throw RegistrationFailedException.IncorrectPasswordException
        }
        if (rememberMe) {
            currentUserDao.insertCurrentUser(CurrentUserEntityMapper(true).mapFromEntity(user))
        } else {
            currentUserDao.insertCurrentUser(CurrentUserEntityMapper(false).mapFromEntity(user))
        }
        this.rememberMe = rememberMe
    }

    override suspend fun changePassword(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun isSignedIn(): Boolean {
        return try {
            if (rememberMe == false) {
                true
            } else {
                currentUserDao.getCurrentUser().signedIn
            }
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun logOut() {
        val currentUser = currentUserDao.getCurrentUser().copy(
            signedIn = false
        )
        rememberMe = true
        currentUserDao.updateCurrentUser(currentUser)
    }
}