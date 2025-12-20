package attendance.validator

import attendance.constants.ErrorMessages
import attendance.model.Attendance
import attendance.view.OutputView
import camp.nextstep.edu.missionutils.DateTimes
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Validator {
    fun isValidNickName(nickName: String): Boolean {
        val nickNameSet = mutableSetOf<String>()
        Attendance.attendanceStatuses.forEach {
            nickNameSet.add(it.nickName)
        }

        return nickNameSet.contains(nickName)
    }

    fun isBizDay(date: LocalDate): Boolean {

        return !(date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY || date.dayOfMonth == 25)

    }

    fun isCurrent(date: LocalDate): Boolean {
        return date.isAfter(DateTimes.now().toLocalDate())
    }

    fun isBizHour(time: LocalTime): Boolean {
        return time.isAfter(LocalTime.of(8, 0)) && time.isBefore(LocalTime.of(23, 0))
    }

    fun isTimeParsable(time: String): Boolean {
        try {
            val parsed = LocalTime.parse(time)
            return true
        } catch (e: DateTimeParseException) {
            return false
        }
    }

    fun isDateParsable(date: String): Boolean {
        try {
            val parsed = LocalDate.parse(date)
            return true
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    fun isFirstAttend(name: String, date: LocalDate) {
        val target = Attendance.attendanceStatuses.find { it.nickName == name && it.dateTime.toLocalDate() == date }
        require(target == null) { ErrorMessages.NOT_FIRST_ATTEND }
    }

}