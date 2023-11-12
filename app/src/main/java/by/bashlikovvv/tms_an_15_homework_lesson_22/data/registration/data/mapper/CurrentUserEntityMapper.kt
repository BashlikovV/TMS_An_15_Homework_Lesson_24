package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.mapper

import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.CurrentUserEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.NotImplementedException
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.IMapper
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.User

class CurrentUserEntityMapper(
    private val signedIn: Boolean
) : IMapper<User, CurrentUserEntity> {
    override fun mapFromEntity(entity: User): CurrentUserEntity {
        return CurrentUserEntity(
            id = 0,
            email = entity.email,
            signedIn = signedIn
        )
    }

    override fun mapToEntity(domain: CurrentUserEntity): User {
        throw NotImplementedException
    }
}