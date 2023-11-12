package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model

interface IMapper<Entity, Domain> {
    fun mapFromEntity(entity: Entity): Domain
    fun mapToEntity(domain: Domain): Entity
}