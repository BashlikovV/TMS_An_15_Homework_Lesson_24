package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.mapper

import by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.model.HistoryEntity
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.IMapper
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon

class HistoryEntityMapper(
    private val userId: Long? = null
) : IMapper<HistoryEntity, ItemCommon> {
    override fun mapFromEntity(entity: HistoryEntity): ItemCommon {
        val header = entity.header
        val body = entity.body
        val time = entity.time

        return if (entity.error) {
            ItemCommon.ErrorItem(header, body, time)
        } else {
            ItemCommon.NoteItem(header, body, time)
        }
    }

    override fun mapToEntity(domain: ItemCommon): HistoryEntity {
        userId ?: throw IllegalStateException()
        val error = domain is ItemCommon.ErrorItem
        val t = when(domain) {
            is ItemCommon.ErrorItem -> {
                Triple(domain.header, domain.text, domain.time)
            }
            is ItemCommon.NoteItem -> {
                Triple(domain.header, domain.text, domain.time)
            }
        }

        return HistoryEntity(
            0, t.first, t.second, t.third, error, userId
        )
    }
}