package com.sedsoftware.remindies.domain

enum class RemindiePeriod(val str: String) {
    NONE("none"),
    HOURLY("hour"),
    DAILY("day"),
    WEEKLY("week"),
    MONTHLY("month"),
    YEARLY("year");
}
