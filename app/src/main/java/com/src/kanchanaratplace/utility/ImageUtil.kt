package com.src.kanchanaratplace.utility
import java.util.UUID


fun getUniqueImageFileName(originalFileName: String): String {
    val fileExtension = originalFileName.substringAfterLast(".", "")
    val uniqueName = UUID.randomUUID().toString()
    return if (fileExtension.isNotEmpty()) {
        "$uniqueName.$fileExtension"
    } else {
        uniqueName
    }
}

