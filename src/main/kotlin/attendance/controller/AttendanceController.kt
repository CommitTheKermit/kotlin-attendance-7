package attendance.controller

import attendance.model.Attendance
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceController {
    fun run() {
        val attendanceList = fileRead()
    }

    fun fileRead() : MutableList<Attendance>{

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
}