package com.soten.genieforum.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun createdTimeForId(): String {
        val currentDateTime = Calendar.getInstance().time
        return SimpleDateFormat("yyMMddHHmmss", Locale.KOREA).format(currentDateTime)
    }

}