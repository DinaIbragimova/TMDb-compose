package com.ibragimova.corecommon.model

import java.time.ZonedDateTime
import java.util.UUID
import kotlinx.serialization.Serializable

typealias Id = Long
typealias DateTime = String
typealias Timestamp = Long
typealias Token = String
typealias PhoneNumber = String
typealias FullUrl = String
typealias PartialUrl = String

fun generateCurrentDateTime() = ZonedDateTime.now().toString()
fun generateRequestId() = UUID.randomUUID().toString()

@Serializable
data class PhoneNumberData(
    val code: Int, // eg 7
    val number: Long, // eg 1234567890
) {

    fun getPhoneNumber(): PhoneNumber = "$code$number"
    fun getPhoneNumberWithPlus(): PhoneNumber = "+${getPhoneNumber()}"
}