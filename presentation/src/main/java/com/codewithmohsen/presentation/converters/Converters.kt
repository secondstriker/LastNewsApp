package com.codewithmohsen.presentation.converters

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Converters {

    fun dateTimeToRequiredFormatForList(dateTime: String?): String? {
        return getAbbreviatedFromDateTime(dateTime, "yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy")
    }

    fun dateTimeToRequiredFormatForDetail(dateTime: String?): String {
        return getAbbreviatedFromDateTime(
            dateTime,
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "HH:mm - dd MMM yyyy"
        ) ?: ""
    }

    private fun getAbbreviatedFromDateTime(
        dateTime: String?,
        dateFormat: String,
        field: String
    ): String? {
        if (dateTime == null)
            return null

        val input = SimpleDateFormat(dateFormat, Locale.US)
        val output = SimpleDateFormat(field, Locale.US)
        try {
            val getAbbreviate = input.parse(dateTime)
                ?: throw ParseException("can not parse dateTime", 0) // parse input
            return output.format(getAbbreviate)    // format output
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return null
    }
}