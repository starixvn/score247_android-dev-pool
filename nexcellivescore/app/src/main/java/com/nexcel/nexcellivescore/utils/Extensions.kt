package com.nexcel.nexcellivescore.utils

import java.util.Date
import java.util.Calendar
import java.util.Locale
import java.util.GregorianCalendar

fun Date.decrease(days: Int): Date {
    return if (days > 0) {
        val calendar = Calendar.getInstance(Locale.US)
        calendar.time = this
        calendar.add(Calendar.DATE, days * -1)
        calendar.time
    } else {
        this
    }
}

fun Date.increase(days: Int): Date {
    return if (days > 0) {
        val calendar = Calendar.getInstance(Locale.US)
        calendar.time = this
        calendar.add(Calendar.DATE, days)
        calendar.time
    } else {
        this
    }
}

fun Date.getShortDayOfWeekText(): String {
    val calendar = GregorianCalendar()
    calendar.time = this
    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).orEmpty()
}

fun Date.getShortMonthText(): String {
    val calendar = GregorianCalendar()
    calendar.time = this
    return calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US).orEmpty()
}

fun Date.getDayOfMonth(): Int {
    val calendar = Calendar.getInstance(Locale.US)
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.getDisplayDateOfMonthText(): String {
    val month = getShortMonthText()
    val dayOfMonth = getDayOfMonth()
    return "$dayOfMonth $month"
}

fun Date.isTheSameDay(date: Date): Boolean {
    val inputCalendar = Calendar.getInstance().apply {
        time = date
    }
    val currentCalendar = Calendar.getInstance().apply {
        time = this@isTheSameDay
    }
    return inputCalendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR) &&
            inputCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)
}

fun Date.getDisplayTime(): String {
    val currentCalendar = Calendar.getInstance().apply {
        time = this@getDisplayTime
    }
    val hoursString = String.format("%02d", currentCalendar.get(Calendar.HOUR_OF_DAY))
    val minutesString = String.format("%02d", currentCalendar.get(Calendar.MINUTE))
    return "$hoursString:$minutesString"
}