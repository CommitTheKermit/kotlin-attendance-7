package attendance.controller

import attendance.constants.ErrorMessages
import attendance.model.AttInfo
import attendance.model.Attendance
import attendance.view.InputView
import attendance.view.OutputView
import attendance.view.output.EditView
import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AttController {
    fun run() {
        val statuses = Attendance.attendanceStatuses

        while (true) {
            OutputView.showRunGuide()
            val runSelect = InputView.readLine()

            when (runSelect) {
                "1" -> {
                    attCheckIn()
                }

                "2" -> {
                    attEdit()
                }

                "3" -> {}
                "4" -> {}
                "Q" -> {
                    break
                }
                else -> {
                    throw IllegalArgumentException(ErrorMessages.ILLEGAL_ARGUMENT)
                }

            }
        }
    }

    fun attCheckIn() {
        OutputView.showNickNameInputGuide()
        val nickName = InputView.readLine()
        OutputView.showTimeInputGuide()
        val time = InputView.readLine()

        val nowDate = DateTimes.now().toLocalDate()
        val localTime: LocalTime = LocalTime.parse(time)
        val dateTime: LocalDateTime = LocalDateTime.of(nowDate, localTime)

        val newAttend = AttInfo(
            nickName = nickName,
            dateTime = dateTime
        )
        Attendance.attendanceStatuses.add(
            newAttend
        )

        OutputView.showSingleStatus(
            attInfo = newAttend,
            attStatus = Attendance.statusJudge(
                attInfo = newAttend
            )
        )
    }

    fun attEdit() {
        EditView.showNickNameInputGuide()
        val nickName = InputView.readLine()
        EditView.showDateInputGuide()
        val date = InputView.readLine()
        EditView.showTimeInputGuide()
        val time = InputView.readLine()

        val nowDate = DateTimes.now().toLocalDate()
        val localTime: LocalTime = LocalTime.parse(time)
        val dateTime: LocalDateTime = LocalDateTime.of(nowDate, localTime)

        val targetDate = LocalDate.of(nowDate.year, nowDate.month, date.toInt())

        val targetInfo =
            Attendance.attendanceStatuses.find {
                it.nickName == nickName
                        && it.dateTime.toLocalDate().isEqual(targetDate)
            }


        val newAttend = AttInfo(
            nickName = nickName,
            dateTime = dateTime
        )
        EditView.showEditedStatus(
            prevAttInfo = targetInfo!!,
            prevAttStatus = Attendance.statusJudge(targetInfo),
            newAttInfo = newAttend,
            newAttStatus = Attendance.statusJudge(newAttend)
        )

        Attendance.attendanceStatuses.remove(targetInfo)
        Attendance.attendanceStatuses.add(newAttend)

    }
}