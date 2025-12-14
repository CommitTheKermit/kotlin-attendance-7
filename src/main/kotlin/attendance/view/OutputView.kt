package attendance.view

import attendance.model.AttInfo
import attendance.model.AttStatus
import camp.nextstep.edu.missionutils.DateTimes
import java.time.format.DateTimeFormatter

object OutputView {
    fun showRunGuide() {
        val now = DateTimes.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE")
        val formatted = now.format(formatter)
        println("오늘은 ${formatted}입니다. 기능을 선택해 주세요.")
        println(
            """
            1. 출석 확인
            2. 출석 수정
            3. 크루별 출석 기록 확인
            4. 제적 위험자 확인
            Q. 종료
        """.trimIndent()
        )
    }

    fun showNickNameInputGuide() {
        println("닉네임을 입력해 주세요.")
    }

    fun showTimeInputGuide() {
        println("등교 시간을 입력해 주세요.")
    }

    fun showSingleStatus(attInfo: AttInfo, attStatus: AttStatus) {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm")
        println("${attInfo.dateTime.format(formatter)} (${attStatus.status})")
    }
}