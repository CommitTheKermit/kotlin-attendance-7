package attendance.controller

import attendance.model.Attendance
import attendance.view.InputView
import attendance.view.OutputView

class AttController {
    fun run() {
        val statuses = Attendance.attendanceStatuses

        OutputView.showRunGuide()
    }
}