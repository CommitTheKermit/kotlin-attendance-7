package attendance.view

import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object OutputView {
    fun showFunctionGuide() {

//        val now = DateTimes.now()
        val now = LocalDateTime.of(2024, 12, 31, 10, 0)
        println(
            """
                오늘은 ${now.format(DateTimeFormatter.ofPattern("MM월 dd일 EEEE"))}입니다. 기능을 선택해 주세요.
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
            """.trimIndent()
        )
    }

    fun showNewLine() {
        println()
    }
}