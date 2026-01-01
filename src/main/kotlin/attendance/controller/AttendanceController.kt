package attendance.controller

import attendance.model.Attendance
import attendance.service.AttendanceService
import attendance.service.FileService
import attendance.service.Parser
import attendance.service.Validator
import attendance.view.AttendanceConfirmView
import attendance.view.InputView
import attendance.view.OutputView
import camp.nextstep.edu.missionutils.DateTimes
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceController {
    val attendanceList: MutableList<Attendance> = FileService.fileRead()
    val attendanceService = AttendanceService(attendanceList)
    fun run() {
        while (true) {

            OutputView.showFunctionGuide()
            val functionNumber = InputView.readLine()
            Validator.functionNumberValidate(functionNumber);
            OutputView.showNewLine()
            when (functionNumber) {
                "1" -> attendanceService.attendanceConfirm()
                "2" -> attendanceService.attendanceEdit()
                "3" -> attendanceService.attendanceJudge()
                "4" -> {}
                "Q" -> {}

            }
        }

    }


}