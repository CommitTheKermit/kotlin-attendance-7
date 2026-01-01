package attendance.controller

import attendance.model.Attendance
import attendance.service.Parser
import attendance.service.Validator
import attendance.view.AttendanceConfirmView
import attendance.view.InputView
import camp.nextstep.edu.missionutils.DateTimes
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceController {
    val attendanceList : MutableList<Attendance> = fileRead()
    fun run() {
        while (true) {
            val functionNumber = InputView.readLine()
            Validator.functionNumberValidate(functionNumber);
            when (functionNumber) {
                "1" -> attendanceConfirm()
                "2" -> {}
                "3" -> {}
                "4" -> {}
                "Q" -> {}

            }
        }

    }

    fun fileRead(): MutableList<Attendance> {

        val attendanceList = mutableListOf<Attendance>()

        val file = File("src/main/resources/attendances.csv")
        val lines = file.readLines().drop(1)

        lines.forEach { line ->
            val split: List<String> = line.split(",")
            val dateTimeSplit = split[1].split(" ")

            val date = LocalDate.parse(dateTimeSplit[0])
            val time = LocalTime.parse(dateTimeSplit[1])
            val dateTime = LocalDateTime.of(date, time)

            val attendance = Attendance(
                nickname = split[0],
                dateTime = dateTime
            )
            attendanceList.add(attendance)
        }
        return attendanceList
    }

    fun attendanceConfirm(){
        AttendanceConfirmView.showNicknameInputGuide()
        val nickname = InputView.readLine()
        Validator.nicknameValidate(nickname, attendanceList)

    }
}