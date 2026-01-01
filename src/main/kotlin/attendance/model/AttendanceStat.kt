package attendance.model

import attendance.constants.AttStatus

class AttendanceStat(val attendances: List<Attendance>) {
    var warning :String = ""
    var lateCount = 0
    var absentCount = 0
    init {

        attendances.forEach {
            when (it.status) {
                AttStatus.ATTEND -> {}
                AttStatus.LATE -> lateCount++
                AttStatus.ABSENT -> absentCount++
            }
        }
        val totalCount = absentCount + lateCount / 3
        if (totalCount > 5) {
            warning = "제적"
        } else if (totalCount > 2) {
            warning = "면담"
        } else if (totalCount > 1) {
            warning = "경고"
        }
    }
}