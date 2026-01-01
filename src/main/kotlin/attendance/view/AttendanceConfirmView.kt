package attendance.view

import attendance.model.Attendance
import java.time.format.DateTimeFormatter

object AttendanceConfirmView {
    fun showNicknameInputGuide() {
        println("닉네임을 입력해 주세요.")
    }

    fun showTimeInputGuide() {
        println("등교 시간을 입력해 주세요.")
    }

    fun showAttendance(attendance: Attendance) {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm");
        println("${formatter.format(attendance.dateTime)} (${attendance.status.status})")
        println()
    }
}