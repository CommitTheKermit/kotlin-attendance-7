package attendance.model

import attendance.constants.ErrorMessages
import attendance.validator.Validator
import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDateTime
import java.time.LocalTime

object CheckIn {
    fun checkIn(time: String, nickName: String): AttInfo {
        val nowDate = DateTimes.now().toLocalDate()
        require(Validator.isBizDay(date = nowDate)) { ErrorMessages.INVALID_ATTEND }
        val localTime: LocalTime = LocalTime.parse(time)
        val dateTime: LocalDateTime = LocalDateTime.of(nowDate, localTime)

        val newAttend = AttInfo(
            nickName = nickName,
            dateTime = dateTime,
            status = Attendance.statusJudge(
                dateTime
            )
        )

        Attendance.attendanceStatuses.add(
            newAttend
        )
        Validator.isFirstAttend(name = nickName, dateTime.toLocalDate())
        return newAttend
    }
}