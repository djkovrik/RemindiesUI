package com.sedsoftware.remindies.domain

enum class RemindiePeriod(val str: String) {
    NONE("N"),
    HOURLY("H"),
    DAILY("D"),
    WEEKLY("W"),
    MONTHLY("M"),
    YEARLY("Y");
}
