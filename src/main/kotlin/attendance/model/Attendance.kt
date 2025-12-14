package attendance.model

import java.io.File
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object Attendance {
    var attendanceStatuses: MutableList<AttInfo>

    init {
        attendanceStatuses = initAttendance()
    }

    fun initAttendance(): MutableList<AttInfo> {
        val file = File("src/main/resources/attendances.csv")
        val lines = file.readLines().drop(1)

        val processStatuses = mutableListOf<AttInfo>()

        lines.forEach {
            val split = it.split(',')

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val dateTime = LocalDateTime.parse(split[1], formatter)
            val status = AttInfo(
                nickName = split[0],
                dateTime = dateTime
            )
            processStatuses.add(status)
        }

        return processStatuses
    }

    fun statusJudge(attInfo: AttInfo): AttStatus {
        val attendStart: LocalTime = when (attInfo.dateTime.dayOfWeek) {
            DayOfWeek.MONDAY -> LocalTime.parse("13:00")
            else -> LocalTime.parse("10:00")
        }

        val attTime = attInfo.dateTime.toLocalTime()
        if (attTime.isAfter(attendStart)) {
            val minuteDiff = ChronoUnit.MINUTES.between(attendStart, attTime)
            if (minuteDiff > 30) {
                return AttStatus.ABSENCE
            } else if (minuteDiff > 5) {
                return AttStatus.LATE
            }
        }
        return AttStatus.ATTEND
    }
}