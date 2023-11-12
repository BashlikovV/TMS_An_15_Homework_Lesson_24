package by.bashlikovvv.tms_an_15_homework_lesson_22.domain

sealed class Exceptions : RuntimeException()

sealed class RegistrationFailedException : Exceptions() {

    data object AccountNotFoundException : RegistrationFailedException()

    data object IncorrectPasswordException : RegistrationFailedException()

    data object AccountAlreadyExistsException : RegistrationFailedException()
}

data object NotImplementedException : Exceptions()