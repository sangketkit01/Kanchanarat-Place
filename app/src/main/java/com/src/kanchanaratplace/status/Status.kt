package com.src.kanchanaratplace.status
sealed interface StatusType

enum class RoomStatus(val id: Int, val status: String) : StatusType {
    ROOM_AVAILABLE(1, "ว่าง"),
    ROOM_OCCUPIED(2, "ไม่ว่าง"),
    ROOM_RESERVED(3,"จอง")
}

enum class OtherStatus(val id: Int , val status : String) : StatusType{
    PENDING(4,"รอดำเนินการ"),
    EXPIRED(5,"เลยกำหนด"),
    APPROVED(6,"อนุมัติ"),
    REJECTED(7,"ปฏิเสธ"),
    FAILED(8,"ล้มเหลว"),
    SUCCESS(9,"สำเร็จ"),
    PREPARE(10,"กำลังดำเนินการ")
}



