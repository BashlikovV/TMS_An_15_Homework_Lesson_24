package by.bashlikovvv.tms_an_15_homework_lesson_22.data.registration.data.local.contract

object RegistrationRoomContract {

    const val DATABASE_NAME = "registration.room"

    object UsersTable {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_HASH = "hash"
        const val COLUMN_SALT = "salt"
        const val COLUMN_TIME = "time"
    }

    object CurrentUserTable {
        const val TABLE_NAME = "current_user"
        const val COLUMN_ID = "id"
        const val COLUMN_CURRENT_USER_EMAIL = "email"
        const val COLUMN_SIGNED_IN = "signed_in"
    }

    object HistoryTable {
        const val TABLE_NAME = "history"
        const val COLUMN_ID = "id"
        const val COLUMN_HEADER = "header"
        const val COLUMN_BODY = "body"
        const val COLUMN_TIME = "time"
        const val COLUMN_ERROR = "error"
        const val COLUMN_USER_ID = "user_id"
    }

}