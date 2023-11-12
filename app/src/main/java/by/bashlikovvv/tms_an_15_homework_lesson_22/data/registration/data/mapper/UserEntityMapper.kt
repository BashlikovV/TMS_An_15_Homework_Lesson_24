package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.mapper

import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.util.SecurityUtilsImpl
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.UserEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.RegistrationFailedException
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.IMapper
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.User
import java.util.Calendar

class UserEntityMapper : IMapper<User, UserEntity> {

    private val securityUtils = SecurityUtilsImpl()

    @Throws(RegistrationFailedException::class)
    override fun mapFromEntity(entity: User): UserEntity {
        if (entity.password.isBlank()) {
            throw RegistrationFailedException.IncorrectPasswordException
        }
        val salt = securityUtils.generateSalt()
        val hash = securityUtils.passwordToHash(entity.password.toCharArray(), salt)
        val time = Calendar.getInstance().time.time

        return UserEntity(
            id = 0,
            email = entity.email,
            salt = securityUtils.bytesToString(salt),
            hash = securityUtils.bytesToString(hash),
            time = time
        )
    }

    override fun mapToEntity(domain: UserEntity): User {
        return User(
            email = domain.email,
            password = domain.hash
        )
    }
}