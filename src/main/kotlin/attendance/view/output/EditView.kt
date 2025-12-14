package attendance.view.output

import attendance.model.AttInfo
import attendance.model.AttStatus
import camp.nextstep.edu.missionutils.DateTimes
import java.time.format.DateTimeFormatter

object EditView {
    fun showNickNameInputGuide() {
        println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
    }

    fun showDateInputGuide() {
        println("수정하려는 날짜(일)를 입력해 주세요.")
    }

    fun showTimeInputGuide() {
        println("언제로 변경하겠습니까?")
    }

    fun showEditedStatus(
        prevAttInfo: AttInfo,
        newAttInfo: AttInfo,
    ) {
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        println(
            "${prevAttInfo.dateTime.format(formatter)} (${prevAttInfo.status.status})" +
                    " -> ${newAttInfo.dateTime.format(timeFormatter)} (${newAttInfo.status.status}) 수정 완료!"
        )
    }

}