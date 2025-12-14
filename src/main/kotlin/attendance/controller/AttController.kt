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

        OutputView.showRunGuide()
        while (true) {
            OutputView.showRunGuide()
            val runSelect = InputView.readLine()

            when (runSelect) {
                "1" -> {
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
}