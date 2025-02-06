package com.src.kanchanaratplace.status
sealed interface StatusType

enum class RoomStatus(val code: Int, val message: String) : StatusType {
    ROOM_AVAILABLE(1, "ว่าง"),
    ROOM_OCCUPIED(2, "ไม่ว่าง"),
    ROOM_RESERVED(12,"จอง")
}

enum class PaymentStatus(val code: Int, val message: String) : StatusType {
    PAYMENT_PENDING(3, "Payment Pending"),
    PAYMENT_SUCCESS(4, "Payment Success"),
    PAYMENT_EXPIRED(5, "Payment Expired")
}

enum class ReservationStatus(val code: Int, val message: String) : StatusType {
    RESERVATION_PENDING(6, "Reservation Pending"),
    RESERVATION_APPROVED(7, "Reservation Approved"),
    RESERVATION_REJECTED(8, "Reservation Rejected")
}

enum class RepairStatus(val code: Int, val message: String) : StatusType {
    REPAIR_PENDING(9, "Repair Pending"),
    REPAIR_SUCCESS(10, "Repair Success"),
    REPAIR_FAILED(11, "Repair Failed")
}



