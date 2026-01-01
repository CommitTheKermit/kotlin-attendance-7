package attendance.constants

enum class AttStatus(val status: String) {
    ATTEND("출석"),
    LATE("지각"),
    ABSENT("결석"),
}