package attendance.view

import attendance.constants.AttStatus
import attendance.model.Attendance
import java.time.format.DateTimeFormatter

object AttendanceJudgeView {
    fun showNicknameInputGuide() {
        println("닉네임을 입력해 주세요.")
    }

    fun showAttendance(attendances: List<Attendance>) {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm")

        var attendCount = 0
        var lateCount = 0
        var absentCount = 0

        attendances.forEach {
            println("${it.dateTime.format(formatter)} (${it.status})")

            when (it.status) {
                AttStatus.ATTEND -> attendCount++
                AttStatus.LATE -> lateCount++
                AttStatus.ABSENT -> absentCount++
            }
        }
        println()
        println(
            """
            출석: ${attendCount}회
            지각: ${lateCount}회
            결석: ${absentCount}회
        """.trimIndent()
        )
        println()
        if (absentCount + lateCount / 3 > 3) {
            println("면담 대상자입니다.\n")
        }
        println()
    }
}