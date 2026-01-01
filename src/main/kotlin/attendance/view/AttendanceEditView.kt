package attendance.view

import attendance.model.Attendance
import java.time.format.DateTimeFormatter

object AttendanceEditView {
    fun showNicknameInputGuide() {
        println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
    }

    fun showDateInputGuide() {
        println("수정하려는 날짜(일)를 입력해 주세요.")
    }

    fun showTimeInputGuide() {
        println("등교 시간을 입력해 주세요.")
    }

    fun showChangedAttendance(prevAttendance: Attendance, attendance: Attendance) {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm")

        print("${formatter.format(prevAttendance.dateTime)} (${prevAttendance.status.status}) -> ")
        println("${attendance.dateTime.format(DateTimeFormatter.ofPattern("hh:mm"))} (${attendance.status.status})")
        println()
    }
}