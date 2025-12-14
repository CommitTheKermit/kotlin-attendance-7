package attendance.view

import camp.nextstep.edu.missionutils.DateTimes
import lotto.constants.Messages
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
}