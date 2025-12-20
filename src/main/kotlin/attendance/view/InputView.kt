package attendance.view

import attendance.constants.ErrorMessages
import attendance.validator.Validator
import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.DateTimes
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object InputView {
    fun readLine(): String {
        val input = Console.readLine()
        println()

        return input ?: ""
    }

    fun readNickName(): String {
        val nickname = readLine()
        require(Validator.isValidNickName(nickname)) { OutputView.showError(ErrorMessages.INVALID_NICKNAME) }

        return nickname
    }

    fun readDate(): String {
        val date = readLine()
        val nowDate = DateTimes.now().toLocalDate()
        val targetDate = LocalDate.of(nowDate.year, nowDate.month, date.toInt())

        require(Validator.isCurrent(targetDate)) { ErrorMessages.NOT_MODIFIABLE }
        require(Validator.isDateParsable(date)) { ErrorMessages.ILLEGAL_ARGUMENT }
        val dateTime = LocalDate.of(2025, 12, date.toInt())
        require(Validator.isBizDay(date = dateTime)) {
            val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE")
            return dateTime.format(formatter) + ErrorMessages.ILLEGAL_ARGUMENT
        }

        return date
    }

    fun readTime(): String {
        val time = readLine()
        require(Validator.isTimeParsable(time)) { OutputView.showError(ErrorMessages.ILLEGAL_ARGUMENT) }
        require(Validator.isBizHour(LocalTime.parse(time))) { OutputView.showError(ErrorMessages.NOT_BIZ_HOUR) }

        return time
    }


}