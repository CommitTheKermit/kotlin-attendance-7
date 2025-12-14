package attendance.model

import camp.nextstep.edu.missionutils.DateTimes
import java.io.File
import java.time.DayOfWeek
import java.time.LocalDate
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
                dateTime = dateTime,
                status = statusJudge(dateTime)
            )
            processStatuses.add(status)
        }

        return processStatuses
    }

    fun statusJudge(dateTime: LocalDateTime): AttStatus {
        val attendStart: LocalTime = when (dateTime.dayOfWeek) {
            DayOfWeek.MONDAY -> LocalTime.parse("13:00")
            else -> LocalTime.parse("10:00")
        }

        val attTime = dateTime.toLocalTime()
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

    fun fillEmptyDate(crewAttInfos: List<AttInfo>): MutableList<AttInfo> {
        val now = DateTimes.now()
        val nowDate = now.toLocalDate().dayOfMonth

        val crewAttInfosWithAbsent = mutableListOf<AttInfo>()

        crewAttInfosWithAbsent.addAll(crewAttInfos)
        for (day: Int in 1..nowDate) {
            val date = LocalDate.of(now.year, now.month, day)
            if(crewAttInfos.find { it.dateTime.toLocalDate().equals(date) } != null){
                continue
            }
            if (date.dayOfMonth == 25) {
                continue
            }
            if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
                continue
            }

            crewAttInfosWithAbsent.add(
                AttInfo(
                    nickName = crewAttInfos.first().nickName,
                    dateTime = LocalDateTime.of(date, LocalTime.of(0, 0)),
                    status = AttStatus.ABSENCE
                )
            )
        }

        return crewAttInfosWithAbsent
    }
}