package attendance.model

import attendance.constants.ErrorMessages

enum class AttStatus(val status: String) {
    LATE("지각"),
    ABSENCE("결석"),
    WARNING("경고"),
    INTERVIEW("면담"),
    EXPEL("제적"),
    ATTEND("출석"),

}
