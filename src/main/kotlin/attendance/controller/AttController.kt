package attendance.controller

import attendance.constants.ErrorMessages
import attendance.model.AttInfo
import attendance.model.AttStatus
import attendance.model.Attendance
import attendance.view.InputView
import attendance.view.OutputView
import camp.nextstep.edu.missionutils.DateTimes
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

                "2" -> {}
                "3" -> {}
                "4" -> {}
                "Q" -> {}
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
            attStatus = AttStatus.ATTEND
        )
    }
}