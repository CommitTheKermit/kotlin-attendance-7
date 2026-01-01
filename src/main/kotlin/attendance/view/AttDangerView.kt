package attendance.view

import attendance.constants.AttStatus
import attendance.model.Attendance
import attendance.model.AttendanceStat
import java.time.format.DateTimeFormatter

object AttDangerView {
    fun showDangerList(stats: List<AttendanceStat>) {
        println("제적 위험자 조회 결과")

        stats.forEach { stat ->
            val nickname = stat.attendances.first().nickname
            println("- ${nickname}: 결석 ${stat.absentCount}회, 지각 ${stat.lateCount}회 (${stat.warning})")
        }


    }
}