package attendance.controller

import attendance.constants.ErrorMessages
import attendance.model.AttInfo
import attendance.model.AttStatus
import attendance.model.Attendance
import attendance.model.DangerCrew
import attendance.view.InputView
import attendance.view.OutputView
import attendance.view.output.CheckCrewAttView
import attendance.view.output.EditView
import attendance.view.output.ExpelDangerView
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

                "3" -> {
                    checkCrewAtt()
                }

                "4" -> {
                    checkDangerCrew()
                }

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
            dateTime = dateTime,
            status = Attendance.statusJudge(
                dateTime
            )
        )
        Attendance.attendanceStatuses.add(
            newAttend
        )

        OutputView.showSingleStatus(
            attInfo = newAttend,

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
            dateTime = dateTime, status = Attendance.statusJudge(dateTime),
        )
        EditView.showEditedStatus(
            prevAttInfo = targetInfo!!,
            newAttInfo = newAttend,
        )

        Attendance.attendanceStatuses.remove(targetInfo)
        Attendance.attendanceStatuses.add(newAttend)
    }

    fun checkCrewAtt() {
        OutputView.showNickNameInputGuide()
        val nickName = InputView.readLine()

        var targetInfos =
            Attendance.attendanceStatuses.filter { it.nickName == nickName }.toMutableList()

        targetInfos = Attendance.fillEmptyDate(crewAttInfos = targetInfos)

        val sortedList = targetInfos.sortedBy { it.dateTime }
        CheckCrewAttView.showCrewAtt(
            name = nickName,
            attInfoList = sortedList
        )


    }

    fun checkDangerCrew() {
        val nickNameSet = mutableSetOf<String>()
        Attendance.attendanceStatuses.forEach {
            nickNameSet.add(it.nickName)
        }


        val dangerCrews = mutableListOf<DangerCrew>()
        nickNameSet.forEach { nickName ->
            var targetInfos =
                Attendance.attendanceStatuses.filter { it.nickName == nickName }.toMutableList()
            targetInfos = Attendance.fillEmptyDate(crewAttInfos = targetInfos)

            val lateCount = targetInfos.filter { it.status == AttStatus.LATE }.size
            val absentCount = targetInfos.filter { it.status == AttStatus.ABSENCE }.size + lateCount / 3

            var actionStatus: String = ""
            if (absentCount > 5) {
                actionStatus = "제적"
            } else if (absentCount >= 3) {
                actionStatus = "면담"
            } else if (absentCount >= 2) {
                actionStatus = "경고"
            }
            dangerCrews.add(
                DangerCrew(
                    nickName = nickName,
                    absentCount = absentCount,
                    lateCount = lateCount,
                    actionStatus = actionStatus
                )
            )
        }

        val sortedList = dangerCrews.sortedWith(
            compareBy({ it.lateCount + it.absentCount * 3 }, { it.nickName })
        ).reversed()
        ExpelDangerView.showExpelDanger(sortedList)
    }

}