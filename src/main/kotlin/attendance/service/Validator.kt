package attendance.service

import attendance.constants.ErrorMessages
import attendance.model.Attendance

object Validator {

    fun functionNumberValidate(input: String) {
        require((input.toInt() in 1..<5) && input == "Q") { ErrorMessages.ILLEGAL_ARGUMENT }
    }

    fun nicknameValidate(input: String, attList: List<Attendance>) {
        val finds = attList.filter { attendance ->
            attendance.nickname == input
        }
        if (finds.isEmpty()){
            throw IllegalArgumentException(ErrorMessages.NICKNAME_NOT_EXIST)
        }
    }
}