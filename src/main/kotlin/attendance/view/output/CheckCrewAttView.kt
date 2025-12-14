package attendance.view.output

import attendance.model.AttInfo
import attendance.model.AttStatus
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object CheckCrewAttView {
    fun showCrewAtt(name: String, attInfoList: List<AttInfo>) {
        println("이번 달 ${name}의 출석 기록입니다.")
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm")
        val absentFormatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE --:--")

        for (it in attInfoList) {
            if (it.dateTime.toLocalTime().compareTo(LocalTime.parse("00:00")) == 0) {
                println("${it.dateTime.format(absentFormatter)} (${it.status.status})")
                continue
            }
            println("${it.dateTime.format(formatter)} (${it.status.status})")

        }

        val attendCount = attInfoList.filter { it.status == AttStatus.ATTEND }.size
        val lateCount = attInfoList.filter { it.status == AttStatus.LATE }.size
        val absentCount = attInfoList.filter { it.status == AttStatus.ABSENCE }.size + lateCount / 3

        var actionStatus: String = "";
        if (absentCount > 5) {
            actionStatus = "제적"
        } else if (absentCount >= 3) {
            actionStatus = "면담"
        } else if (absentCount >= 2) {
            actionStatus = "경고"
        }

        println(
            """
                
            출석: ${attendCount}회
            지각: ${lateCount}회
            결석: ${absentCount}회
            
        """.trimIndent()
        )
        if (actionStatus.isNotEmpty()) {
            println("$actionStatus 대상자입니다.")
        }


    }


}