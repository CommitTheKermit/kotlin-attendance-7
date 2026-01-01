package attendance.service

import attendance.constants.ErrorMessages
import attendance.model.Attendance
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Validator {

    fun functionNumberValidate(input: String) {
        require((input.toInt() in 1..<6) || input == "Q") { ErrorMessages.ILLEGAL_ARGUMENT }
    }

    fun nicknameValidate(input: String, attList: List<Attendance>) {
        val finds = attList.filter { attendance ->
            attendance.nickname == input
        }
        if (finds.isEmpty()) {
            throw IllegalArgumentException(ErrorMessages.NICKNAME_NOT_EXIST)
        }
    }

    fun timeValidate(time: LocalTime) {
        val startTime = LocalTime.of(8, 0)
        val endTime = LocalTime.of(23, 0)
        require(time.isAfter(startTime) && time.isBefore(endTime)) {
            ErrorMessages.NOT_SERVICE_TIME
        }

    }

    fun dateValidate(date: LocalDate) {
        require(date.dayOfWeek != DayOfWeek.SATURDAY && date.dayOfWeek != DayOfWeek.SUNDAY) {
            "[ERROR] ${date.format(DateTimeFormatter.ofPattern("MM월 dd일 EEEE"))}은 등교일이 아닙니다."
        }
        require(date.dayOfMonth != 25) {
            "[ERROR] ${date.format(DateTimeFormatter.ofPattern("MM월 dd일 EEEE"))}은 등교일이 아닙니다."
        }
    }
}