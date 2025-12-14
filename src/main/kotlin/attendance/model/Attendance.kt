package attendance.model

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Attendance {
    var attendanceStatuses: MutableList<AttInfo>

    init {
        attendanceStatuses = initAttendance()
    }

    fun initAttendance(): MutableList<AttInfo> {
        val file = File("src/main/resources/attendances.csv")
        val lines = file.readLines().drop(1)

        val processStatuses = mutableListOf<AttInfo>()

        lines.forEach {
            val split = it.split(',')

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val dateTime = LocalDateTime.parse(split[1], formatter)
            val status = AttInfo(
                nickName = split[0],
                dateTime = dateTime
            )
            processStatuses.add(status)
        }

        return processStatuses
    }
}