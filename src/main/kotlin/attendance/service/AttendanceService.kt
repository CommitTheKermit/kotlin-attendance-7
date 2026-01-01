package attendance.service

import attendance.constants.ErrorMessages
import attendance.model.Attendance
import attendance.view.AttendanceConfirmView
import attendance.view.AttendanceEditView
import attendance.view.AttendanceJudgeView
import attendance.view.InputView
import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceService(val attendanceList: MutableList<Attendance>) {
    fun attendanceConfirm() {
        AttendanceConfirmView.showNicknameInputGuide()
        val nickname = InputView.readLine()
        Validator.nicknameValidate(nickname, attendanceList)

        AttendanceConfirmView.showTimeInputGuide()
        val time: LocalTime = Parser.timeParse(InputView.readLine())
        Validator.timeValidate(time)

//        val now = DateTimes.now()
        // 디버깅용 임시 코드 (2024년 12월 2일 월요일 기준)
        val now = LocalDateTime.of(2024, 12, 2, 10, 0)
        val attendance = Attendance(
            nickname, LocalDateTime.of(now.toLocalDate(), time)
        )
        attendanceList.add(
            attendance
        )

        AttendanceConfirmView.showAttendance(attendance)
    }

    fun attendanceEdit() {
        AttendanceEditView.showNicknameInputGuide()
        val nickname = InputView.readLine()
        Validator.nicknameValidate(nickname, attendanceList)

        AttendanceEditView.showDateInputGuide()
        val date: LocalDate = Parser.dateParse(InputView.readLine())
        Validator.dateValidate(date)

        var target: Attendance? = attendanceList.find { attendance ->
            attendance.dateTime.dayOfMonth == date.dayOfMonth && attendance.nickname == nickname
        }
        require(target != null) { ErrorMessages.ILLEGAL_ARGUMENT }

        AttendanceEditView.showTimeInputGuide()
        val time: LocalTime = Parser.timeParse(InputView.readLine())
        Validator.timeValidate(time)

        val attendance = Attendance(
            nickname, LocalDateTime.of(date, time)
        )

        AttendanceEditView.showChangedAttendance(
            prevAttendance = target,
            attendance = attendance
        )

        val index = attendanceList.indexOfFirst { attendance ->
            attendance.dateTime.dayOfMonth == date.dayOfMonth && attendance.nickname == nickname
        }
        attendanceList[index] = attendance
        print(attendanceList)
    }

    fun attendanceJudge() {
        AttendanceJudgeView.showNicknameInputGuide()
        val nickname = InputView.readLine()
        Validator.nicknameValidate(nickname, attendanceList)

        attendanceList.filter { it.nickname == nickname }

        AttendanceJudgeView.showAttendance(attendanceList)

    }
}