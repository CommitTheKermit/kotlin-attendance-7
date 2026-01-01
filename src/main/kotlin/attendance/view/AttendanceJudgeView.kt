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
        attendances.forEach {
            println("${it.dateTime.format(formatter)} (${it.status})")

        }

    }
}