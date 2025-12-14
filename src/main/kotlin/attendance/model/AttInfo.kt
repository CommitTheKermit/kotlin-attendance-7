package attendance.model

import java.time.LocalDateTime

data class AttInfo(val nickName: String, val dateTime: LocalDateTime, val status: AttStatus)