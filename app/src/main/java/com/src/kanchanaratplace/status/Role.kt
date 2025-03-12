package com.src.kanchanaratplace.status

enum class Role(val id : Int , val role : String) {
        OWNER(1,"Owner"),
        MEMBER(2,"Member"),
        LEFT_MEMBER(3,"Left Member")
}