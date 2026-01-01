package attendance.service

import attendance.constants.ErrorMessages
import camp.nextstep.edu.missionutils.DateTimes
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalDateTime
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

    fun dateParse(input: String): LocalDate {
        val date: LocalDate
        val now = LocalDateTime.of(2024, 12, 2, 10, 0)
        try {
            date = LocalDate.of(now.year, now.month, input.toInt())
        } catch (e: DateTimeException) {
            throw IllegalArgumentException(ErrorMessages.ILLEGAL_ARGUMENT)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(ErrorMessages.ILLEGAL_ARGUMENT)
        }

        return date
    }
}