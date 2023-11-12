package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.repository

import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.User

interface IRegistrationRepository {

    suspend fun signUp(user: User)

    suspend fun signIn(user: User, rememberMe: Boolean)

    suspend fun changePassword(user: User)

    suspend fun isSignedIn(): Boolean

    suspend fun logOut()
}