package attendance.service

import attendance.constants.ErrorMessages
import attendance.model.Attendance
import attendance.model.AttendanceStat
import attendance.view.AttDangerView
import attendance.view.AttendanceConfirmView
import attendance.view.AttendanceEditView
import attendance.view.AttendanceJudgeView
import attendance.view.InputView
import camp.nextstep.edu.missionutils.DateTimes
import java.time.DayOfWeek
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
        val now = LocalDateTime.of(2024, 12, 31, 10, 0)
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

        var filteredList = attendanceList.filter { it.nickname == nickname }.toMutableList()

        filteredList = fillAbsentAtt(nickname, filteredList)

        AttendanceJudgeView.showAttendance(filteredList.sortedBy { it.dateTime })

    }

    fun fillAbsentAtt(nickname: String, filteredList: MutableList<Attendance>): MutableList<Attendance> {
        val now = LocalDateTime.of(2024, 12, 12, 10, 0)
        val startDay = 1;
        val endDay = now.dayOfMonth
        for (i in startDay..endDay) {
            val date = LocalDate.of(
                now.year, now.month.value, i
            )
            if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY || date.dayOfMonth == 25) {
                continue
            }
            val target = filteredList.find { att ->
                att.dateTime.toLocalDate().compareTo(
                    date
                ) == 0
            }
            if (target == null) {
                filteredList.add(
                    Attendance(
                        nickname = nickname,
                        dateTime = LocalDateTime.of(date, LocalTime.of(23, 59))
                    )
                )
            }
        }
        return filteredList
    }

    fun attDangerCheck() {
        val nicknames = attendanceList.distinctBy { it.nickname }.map { it.nickname }
        val stats = mutableListOf<AttendanceStat>()
        for (nickname in nicknames) {
            var targetList = attendanceList.filter { att -> att.nickname == nickname }.toMutableList()
            targetList = fillAbsentAtt(targetList.first().nickname, targetList)
            val stat = AttendanceStat(targetList)
            if (stat.warning.isEmpty()){
                continue
            }

            stats.add(stat)
        }

        AttDangerView.showDangerList(
            stats = stats.sortedBy {
                it.absentCount + it.lateCount / 3
            }.reversed()
        );
    }
}