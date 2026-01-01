package attendance.model

import attendance.constants.AttStatus
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class Attendance(val nickname: String, val dateTime: LocalDateTime) {
    val status: AttStatus

    init {
        status = statusJudge(dateTime)
    }

    fun statusJudge(dateTime: LocalDateTime): AttStatus {
        var startTime = LocalTime.of(10, 0)
        if (dateTime.dayOfWeek == DayOfWeek.MONDAY) {
            startTime = LocalTime.of(13, 0)
        }

        val time = dateTime.toLocalTime()

        val diffMinutes = java.time.temporal.ChronoUnit.MINUTES.between(startTime, time)
        if (diffMinutes > 30) {
            return AttStatus.ABSENT
        }
        if (diffMinutes > 5) {
            return AttStatus.LATE
        }

        return AttStatus.ATTEND
    }
}

//- 교육 시간은 월요일은 13:00~18:00, 화요일~금요일은 10:00~18:00이다.
//- 해당 요일의 시작 시각으로부터 5분 초과는 지각으로 간주한다.
//- 해당 요일의 시작 시각으로부터 30분 초과는 결석으로 간주한다.
//- 등교하지 않아 출석 기록이 없는 날은 결석으로 간주한다.
// 캠퍼스 운영 시간은 매일 08:00~23:00이다.