package np.com.kirangyawali.composeNepaliDate.date

import androidx.annotation.IntRange
import androidx.core.util.forEach
import np.com.kirangyawali.composeNepaliDate.NepaliDateConstants.maxNepYear
import np.com.kirangyawali.composeNepaliDate.NepaliDateConstants.maxNepaliDate
import np.com.kirangyawali.composeNepaliDate.NepaliDateConstants.minNepYear
import np.com.kirangyawali.composeNepaliDate.NepaliDateConstants.minNepaliDate


internal data class YearMonth(
    @IntRange(from = minNepYear.toLong(), to = maxNepYear.toLong())
    var year: Int,
    @IntRange(from = 1, to = 12)
    var month: Int
)

data class NepaliDate(
    @IntRange(from = minNepYear.toLong(), to = maxNepYear.toLong())
    var year: Int,
    @IntRange(from = 1, to = 12)
    var month: Int,
    @IntRange(from = 1, to = 32)
    var day: Int,
    @IntRange(from = 1, to = 7)
    var dayOfWeek: Int = NepaliDateUtils.fillMissingWeekDayValue(
        NepaliDate(year, month, day, 1)
    ).dayOfWeek
) : Comparable<NepaliDate> {

    infix fun inBounds(boundDate: Pair<NepaliDate?, NepaliDate?>): NepaliDate {

        val minDate = boundDate.first ?: minNepaliDate
        val maxDate = boundDate.second ?: maxNepaliDate

        return if (this in minDate..maxDate) this
        else {
            if (this < minDate) minDate else maxDate
        }
    }

    override fun compareTo(other: NepaliDate): Int {
        val thisDate = ("$year" + "$month".padStart(2, '0') + "$day".padStart(2, '0')).toLong()
        val otherDate =
            ("${other.year}" + "${other.month}".padStart(2, '0') + "${other.day}".padStart(
                2,
                '0'
            )).toLong()

        return when {
            thisDate > otherDate -> {
                1
            }
            thisDate == otherDate -> {
                0
            }
            else -> {
                -1
            }
        }
    }

    fun isSameMonthYear(date: NepaliDate): Boolean =
        this.year == date.year && this.month == date.month
}

internal fun getSelectableYearMonth(minDate: NepaliDate, maxDate: NepaliDate): List<YearMonth> =
    mutableListOf<YearMonth>().apply {
        NepaliDateUtils.daysInMonthMap.forEach { key, _ ->
            if (key in minDate.year..maxDate.year) {
                for (i in 1..12) {
                    if (key == minDate.year && key == maxDate.year) {
                        if (i in minDate.month..maxDate.month) {
                            add(YearMonth(key, i))
                        }
                    } else if (key != maxDate.year && key != minDate.year) {
                        add(YearMonth(key, i))
                    } else {
                        if (key == minDate.year && i >= minDate.month) {
                            add(YearMonth(key, i))
                        } else if (key == maxDate.year && i <= maxDate.month) {
                            add(YearMonth(key, i))
                        }
                    }
                }
            }
        }
    }
