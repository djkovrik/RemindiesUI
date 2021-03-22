package com.sedsoftware.remindies.data

import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.domain.RemindieType
import com.sedsoftware.remindies.domain.entity.Remindie
import com.sedsoftware.remindies.domain.entity.Shot
import kotlinx.coroutines.delay
import kotlinx.datetime.*

// Stubs from Remindies

object LeapDividers {
    const val LEAP_DIVIDER = 4
    const val LEAP_YEAR_MOD1 = 100
    const val LEAP_YEAR_MOD2 = 400
}

object MonthNumbers {
    const val JANUARY = 1
    const val FEBRUARY = 2
    const val MARCH = 3
    const val APRIL = 4
    const val MAY = 5
    const val JUNE = 6
    const val JULY = 7
    const val AUGUST = 8
    const val SEPTEMBER = 9
    const val OCTOBER = 10
    const val NOVEMBER = 11
    const val DECEMBER = 12
}

object MonthDays {
    const val JANUARY = 31
    const val FEBRUARY = 28
    const val FEBRUARY_LEAP = 29
    const val MARCH = 31
    const val APRIL = 30
    const val MAY = 31
    const val JUNE = 30
    const val JULY = 31
    const val AUGUST = 31
    const val SEPTEMBER = 30
    const val OCTOBER = 31
    const val NOVEMBER = 30
    const val DECEMBER = 31
}

object RepositoryStub {

    private val Int.isLeap: Boolean
        get() = when {
            this % LeapDividers.LEAP_DIVIDER == 0 -> {
                when {
                    this % LeapDividers.LEAP_YEAR_MOD1 == 0 -> this % LeapDividers.LEAP_YEAR_MOD2 == 0
                    else -> true
                }
            }
            else -> false
        }

    @Suppress("ComplexMethod")
    private fun Int.days(leap: Boolean): Int =
        when (this) {
            MonthNumbers.JANUARY -> MonthDays.JANUARY
            MonthNumbers.FEBRUARY -> if (leap) MonthDays.FEBRUARY_LEAP else MonthDays.FEBRUARY
            MonthNumbers.MARCH -> MonthDays.MARCH
            MonthNumbers.APRIL -> MonthDays.APRIL
            MonthNumbers.MAY -> MonthDays.MAY
            MonthNumbers.JUNE -> MonthDays.JUNE
            MonthNumbers.JULY -> MonthDays.JULY
            MonthNumbers.AUGUST -> MonthDays.AUGUST
            MonthNumbers.SEPTEMBER -> MonthDays.SEPTEMBER
            MonthNumbers.OCTOBER -> MonthDays.OCTOBER
            MonthNumbers.NOVEMBER -> MonthDays.NOVEMBER
            MonthNumbers.DECEMBER -> MonthDays.DECEMBER
            else -> error("Wrong month value")
        }


    @Suppress("ComplexMethod")
    private fun LocalDateTime.plusPeriod(
        period: RemindiePeriod,
        each: Int,
        timeZone: TimeZone
    ): LocalDateTime =
        when (period) {
            RemindiePeriod.HOURLY -> {
                toInstant(timeZone).plus(each, DateTimeUnit.HOUR, timeZone)
                    .toLocalDateTime(timeZone)
            }
            RemindiePeriod.DAILY -> {
                toInstant(timeZone).plus(each, DateTimeUnit.DAY, timeZone).toLocalDateTime(timeZone)
            }
            RemindiePeriod.WEEKLY -> {
                toInstant(timeZone).plus(each, DateTimeUnit.WEEK, timeZone)
                    .toLocalDateTime(timeZone)
            }
            RemindiePeriod.MONTHLY -> {
                val yearModifier = (monthNumber + each) / Month.values().size
                val nextYear = year + yearModifier

                var nextMonthNumber = (monthNumber + each) % Month.values().size
                if (nextMonthNumber == 0) nextMonthNumber++

                val daysInNextMonth = nextMonthNumber.days(nextYear.isLeap)
                val dayOfNextMonth =
                    if (dayOfMonth > daysInNextMonth) daysInNextMonth else dayOfMonth

                LocalDateTime(nextYear, nextMonthNumber, dayOfNextMonth, hour, minute)
            }
            RemindiePeriod.YEARLY -> {
                val nextYear = year + each

                // leap year hack
                when {
                    // current is leap - next is leap
                    year.isLeap &&
                        monthNumber == MonthNumbers.FEBRUARY &&
                        dayOfMonth == MonthDays.FEBRUARY_LEAP &&
                        nextYear.isLeap ->
                        LocalDateTime(nextYear, month, MonthDays.FEBRUARY_LEAP, hour, minute)

                    // current is leap - next is non leap
                    year.isLeap &&
                        monthNumber == MonthNumbers.FEBRUARY &&
                        dayOfMonth == MonthDays.FEBRUARY_LEAP &&
                        !nextYear.isLeap ->
                        LocalDateTime(nextYear, month, MonthDays.FEBRUARY, hour, minute)

                    // current is non leap - next is leap
                    !year.isLeap &&
                        monthNumber == MonthNumbers.FEBRUARY &&
                        dayOfMonth == MonthDays.FEBRUARY &&
                        nextYear.isLeap ->
                        LocalDateTime(nextYear, month, MonthDays.FEBRUARY_LEAP, hour, minute)

                    // current is non leap - next is non leap
                    !year.isLeap &&
                        monthNumber == MonthNumbers.FEBRUARY &&
                        dayOfMonth == MonthDays.FEBRUARY &&
                        !nextYear.isLeap ->
                        LocalDateTime(nextYear, month, MonthDays.FEBRUARY, hour, minute)

                    else -> LocalDateTime(nextYear, month, dayOfMonth, hour, minute)
                }
            }
            RemindiePeriod.NONE -> this
        }


    private fun Remindie.toNearestShot(
        today: LocalDateTime = Clock.System.now().toLocalDateTime(timeZone)
    ): Shot {

        // Not fired yet
        if (shot > today) {
            return Shot(remindie = this, planned = shot, isFired = false)
        }

        // Already fired for oneshot
        if (period == RemindiePeriod.NONE) {
            return Shot(remindie = this, planned = shot, isFired = true)
        }

        var closest: LocalDateTime = shot

        while (closest < today) {
            closest = closest.plusPeriod(period, each, timeZone)
        }

        return Shot(remindie = this, planned = closest, isFired = false)
    }


    // 5.11.2020 20:55 - Thursday
    private val today: LocalDateTime = LocalDateTime(2020, 11, 5, 10, 20)
    private val timeZone: TimeZone = TimeZone.currentSystemDefault()

    private val remindiesInOwnTimeZone: List<Remindie> = listOf(
        Remindie(
            timestamp = 1,
            created = today,
            shot = LocalDateTime(2020, 11, 6, 15, 35),
            timeZone = timeZone,
            title = "Oneshot - tomorrow",
            type = RemindieType.AIRPORT,
            period = RemindiePeriod.NONE,
            each = 0
        ),

        Remindie(
            timestamp = 2,
            created = today,
            shot = LocalDateTime(2020, 11, 5, 12, 0),
            timeZone = timeZone,
            title = "Each 3 hours from today starting at 12:00",
            type = RemindieType.CALL,
            period = RemindiePeriod.HOURLY,
            each = 3
        ),

        Remindie(
            timestamp = 3,
            created = today,
            shot = LocalDateTime(2020, 11, 6, 8, 0),
            timeZone = timeZone,
            title = "Daily - from tomorrow at 8:00",
            type = RemindieType.CAFE,
            period = RemindiePeriod.DAILY,
            each = 1
        ),

        Remindie(
            timestamp = 4,
            created = today,
            shot = LocalDateTime(2020, 11, 6, 11, 22),
            timeZone = timeZone,
            title = "Daily - from tomorrow each 3 days at 11:22",
            type = RemindieType.CALL,
            period = RemindiePeriod.DAILY,
            each = 3
        ),

        Remindie(
            timestamp = 5,
            created = today,
            shot = LocalDateTime(2020, 11, 7, 21, 30),
            timeZone = timeZone,
            title = "Each week from Saturday at 21:30",
            type = RemindieType.GYM,
            period = RemindiePeriod.WEEKLY,
            each = 1
        ),

        Remindie(
            timestamp = 6,
            created = today,
            shot = LocalDateTime(2020, 11, 8, 16, 0),
            timeZone = timeZone,
            title = "Each two weeks from Sunday at 16:00",
            type = RemindieType.DOCTOR,
            period = RemindiePeriod.WEEKLY,
            each = 2
        ),

        Remindie(
            timestamp = 7,
            created = today,
            shot = LocalDateTime(2020, 11, 8, 18, 0),
            timeZone = timeZone,
            title = "Pay rent each month at 18:00",
            type = RemindieType.PAY,
            period = RemindiePeriod.MONTHLY,
            each = 1
        ),

        Remindie(
            timestamp = 8,
            created = today,
            shot = LocalDateTime(2020, 12, 31, 23, 0),
            timeZone = timeZone,
            title = "Congratulations each New Year night ^-^ at 23:00",
            type = RemindieType.CALL,
            period = RemindiePeriod.YEARLY,
            each = 1
        ),
    )

    suspend fun getShots(): List<Shot> {
        delay(3_000L)
        return remindiesInOwnTimeZone.map { it.toNearestShot() }
    }
}
