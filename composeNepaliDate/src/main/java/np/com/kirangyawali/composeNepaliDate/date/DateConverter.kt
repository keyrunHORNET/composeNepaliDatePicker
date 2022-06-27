package np.com.kirangyawali.composeNepaliDate.date

import androidx.annotation.IntRange
import np.com.kirangyawali.composeNepaliDate.date.NepaliDateUtils.isEngDateInConversionRange
import np.com.kirangyawali.composeNepaliDate.date.NepaliDateUtils.isNepDateInConversionRange
import org.joda.time.DateTime
import org.joda.time.Days
import java.util.*

/**
 * todo simplify converters
 */

/**
 * convert nepali date into english date
 *
 * @param nepYY `int` year of nepali date [1970-2090]
 * @param nepMM `int` month of nepali date [1-12]
 * @param nepDD `int` day of a nepali date [1-32]
 * @return [Calendar] object with the converted value from nepali to english
 */
internal fun convertToAd(
    @IntRange(from = 1970, to = 2090) nepYY: Int,
    @IntRange(from = 1, to = 12) nepMM: Int,
    @IntRange(from = 1, to = 32) nepDD: Int
): Calendar {
    return if (isNepDateInConversionRange(nepYY, nepMM, nepDD)
    ) {
        val startingEngYear = 1913
        val startingEngMonth = 4
        val startingEngDay = 13
        val startingDayOfWeek =
            Calendar.SUNDAY // 1970/1/1 is Sunday /// based on www.ashesh.com.np/neplai-date-converter
        val startingNepYear = 1970
        val startingNepMonth = 1
        val startingNepDay = 1
        var engMM: Int
        var engDD: Int
        var totalNepDaysCount: Long = 0

        //*count total no of days in nepali year from our starting range*//
        for (i in startingNepYear until nepYY) {
            for (j in 1..12) {
                totalNepDaysCount += NepaliDateUtils.daysInMonthMap.get(i)[j]
            }
        }
        /*count total days in terms of month*/
        for (j in startingNepMonth until nepMM) {
            totalNepDaysCount += NepaliDateUtils.daysInMonthMap.get(nepYY)[j]
        }
        /*count total days in terms of date*/
        totalNepDaysCount += (nepDD - startingNepDay).toLong()
        val daysInMonth = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        val daysInMonthOfLeapYear = intArrayOf(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var engYY: Int = startingEngYear
        engMM = startingEngMonth
        engDD = startingEngDay
        var endDayOfMonth: Int
        var dayOfWeek = startingDayOfWeek
        while (totalNepDaysCount != 0L) {
            endDayOfMonth = if (isEngLeapYear(engYY)) {
                daysInMonthOfLeapYear[engMM]
            } else {
                daysInMonth[engMM]
            }
            engDD++
            dayOfWeek++
            if (engDD > endDayOfMonth) {
                engMM++
                engDD = 1
                if (engMM > 12) {
                    engYY++
                    engMM = 1
                }
            }
            if (dayOfWeek > 7) {
                dayOfWeek = 1
            }
            totalNepDaysCount--
        }
        GregorianCalendar(engYY, engMM - 1, engDD, 0, 0, 0)
    } else throw IllegalArgumentException("Out of Range: Date is out of range to Convert")
}


/**
 * convert english date into nepali date
 *
 * @param engYY `int` year of nepali date [1944-2033]
 * @param engMM `int` month of nepali date [1-12]
 * @param engDD `int` day of a nepali date [1-31]
 * @return return nepali date as a [NepaliDate] object converted from english to nepali
 */
internal fun convertToBs(
    @IntRange(from = 1913 - 2033) engYY: Int,
    @IntRange(from = 1, to = 12) engMM: Int,
    @IntRange(from = 1, to = 31) engDD: Int
): NepaliDate {
    return if (isEngDateInConversionRange(engYY, engMM, engDD)
    ) {
        val startingEngYear = 1913
        val startingEngMonth = 4
        val startingEngDay = 13
        val startingDayOfWeek = Calendar.SUNDAY // 1913/4/13 is a Sunday
        val startingNepYear = 1970
        val startingNepMonth = 1
        val startingNepDay = 1
        var dayOfWeek = startingDayOfWeek


        /*calculate the days between two english date*/
        val base =
            DateTime(startingEngYear, startingEngMonth, startingEngDay, 0, 0) // June 20th, 2010
        val newDate = DateTime(engYY, engMM, engDD, 0, 0) // July 24th
        var totalEngDaysCount = Days.daysBetween(base, newDate).days
        var nepYY: Int = startingNepYear
        var nepMM = startingNepMonth
        var nepDD = startingNepDay
        while (totalEngDaysCount != 0) {
            val daysInMonth: Int =
                NepaliDateUtils.daysInMonthMap.get(nepYY).get(nepMM)
            nepDD++
            if (nepDD > daysInMonth) {
                nepMM++
                nepDD = 1
            }
            if (nepMM > 12) {
                nepYY++
                nepMM = 1
            }
            dayOfWeek++
            if (dayOfWeek > 7) {
                dayOfWeek = 1
            }
            totalEngDaysCount--
        }
        NepaliDate(nepYY, nepMM, nepDD, dayOfWeek)
    } else throw IllegalArgumentException("Out of Range: Date is out of range to Convert")
}

private fun isEngLeapYear(year: Int): Boolean {
    val cal = Calendar.getInstance()
    cal[Calendar.YEAR] = year
    return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365
}

