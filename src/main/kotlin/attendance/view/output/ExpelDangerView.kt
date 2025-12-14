package attendance.view.output

import attendance.model.DangerCrew

object ExpelDangerView {
    fun showExpelDanger(dangerCrews: List<DangerCrew>) {
        println("제적 위험자 조회 결과")

        dangerCrews.forEach {
            println("- ${it.nickName}: 결석 ${it.absentCount}회, 지각 ${it.lateCount}회 (${it.actionStatus})")
        }
        println()
    }


}