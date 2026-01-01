package attendance.service

import attendance.constants.ErrorMessages
import java.time.LocalTime
import java.time.format.DateTimeParseException

object Parser {
    fun functionNumberParse(input: String) {
        require((input.toInt() in 1..<5) && input == "Q") { ErrorMessages.ILLEGAL_ARGUMENT }
    }

    fun timeParse(input: String): LocalTime {
        val time: LocalTime
        try {
            time = LocalTime.parse(input)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException(ErrorMessages.ILLEGAL_ARGUMENT)
        }

        return time
    }
}