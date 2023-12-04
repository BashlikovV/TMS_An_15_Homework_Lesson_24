package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model

data class CurrentUserInfo(
    val email: String = "",
    val hash: String = "",
    val salt: String = "",
    val lasConnectionTime: Long = 0
)