package attendance.view

import attendance.constants.AttStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object AttendanceConfirmView {
    fun showNicknameInputGuide() {
        println("닉네임을 입력해 주세요.")
    }

    fun showTimeInputGuide() {
        println("등교 시간을 입력해 주세요.")
    }

    fun showAttendance(dateTime: LocalDateTime, status: AttStatus) {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEE HH:mm");
        println("${formatter.format(dateTime)} (${status.name})")
    }
}