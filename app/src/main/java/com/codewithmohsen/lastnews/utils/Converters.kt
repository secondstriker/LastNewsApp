package com.codewithmohsen.lastnews.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Converters {


    companion object {

        @JvmStatic
        fun dateTimeToRequiredFormatForList(dateTime: String?): String {
            return getAbbreviatedFromDateTime(dateTime, "yyyy-MM-dd'T'HH:mm:ss'Z'", "dd MMM yyyy") ?: ""
        }

        @JvmStatic
        fun dateTimeToRequiredFormatForDetail(dateTime: String?): String {
            return getAbbreviatedFromDateTime(dateTime, "yyyy-MM-dd'T'HH:mm:ss'Z'", "HH:mm - dd MMM yyyy") ?: ""
        }

        @JvmStatic
        private fun getAbbreviatedFromDateTime(dateTime: String?, dateFormat: String, field: String): String? {
            if(dateTime == null)
                return null

            val input = SimpleDateFormat(dateFormat, Locale.US)
            val output = SimpleDateFormat(field, Locale.US)
            try {
                val getAbbreviate = input.parse(dateTime)    // parse input
                return output.format(getAbbreviate)    // format output
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        }
    }
}