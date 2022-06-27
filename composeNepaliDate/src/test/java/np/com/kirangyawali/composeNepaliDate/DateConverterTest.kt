package np.com.kirangyawali.composeNepaliDate

import np.com.kirangyawali.composeNepaliDate.date.NepaliDate
import np.com.kirangyawali.composeNepaliDate.date.NepaliDateUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*

@RunWith(RobolectricTestRunner::class)
class DateConverterTest {

    @Test
    fun aTestConversion() {
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(2019, 7, 1)
        Assert.assertEquals(nepaliDate.day, 16)
        Assert.assertEquals(nepaliDate.month, (3)) //ashar
        Assert.assertEquals(nepaliDate.year, (2076))

        val englishDate: Calendar = NepaliDateUtils.bsToAd(2076, 3, 16)
        Assert.assertEquals(englishDate.get(Calendar.DAY_OF_MONTH), (1))
        Assert.assertEquals(englishDate.get(Calendar.MONTH), (6)) //july
        Assert.assertEquals(englishDate.get(Calendar.YEAR), (2019))
    }

    @Test
    fun bTestConversion() {
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(2007, 2, 19)
        Assert.assertEquals(nepaliDate.day, (7))
        Assert.assertEquals(nepaliDate.month, (11)) //falgun
        Assert.assertEquals(nepaliDate.year, (2063))
        val englishDate: Calendar = NepaliDateUtils.bsToAd(2063, 11, 7)
        Assert.assertEquals(englishDate.get(Calendar.DAY_OF_MONTH), (19))
        Assert.assertEquals(englishDate.get(Calendar.MONTH), (1)) //feb
        Assert.assertEquals(englishDate.get(Calendar.YEAR), (2007))
    }

    @Test
    fun cTestConversion() {
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(1997, 2, 18)
        Assert.assertEquals(nepaliDate.day, (7))
        Assert.assertEquals(nepaliDate.month, (11)) //falgun
        Assert.assertEquals(nepaliDate.year, (2053))
        val englishDate: Calendar = NepaliDateUtils.bsToAd(2053, 11, 7)
        Assert.assertEquals(englishDate.get(Calendar.DAY_OF_MONTH), (18))
        Assert.assertEquals(englishDate.get(Calendar.MONTH), (1)) //feb
        Assert.assertEquals(englishDate.get(Calendar.YEAR), (1997))
    }

    @Test
    fun dTestConversion() {
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(2027, 2, 19)
        Assert.assertEquals(nepaliDate.day, (7))
        Assert.assertEquals(nepaliDate.month, (11)) //falgun
        Assert.assertEquals(nepaliDate.year, (2083))
        val englishDate: Calendar = NepaliDateUtils.bsToAd(2083, 11, 7)
        Assert.assertEquals(englishDate.get(Calendar.DAY_OF_MONTH), (19))
        Assert.assertEquals(englishDate.get(Calendar.MONTH), (1)) //feb
        Assert.assertEquals(englishDate.get(Calendar.YEAR), (2027))
    }

    @Test
    fun eTestConversion() {
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(2017, 2, 18)
        Assert.assertEquals(nepaliDate.day, (7))
        Assert.assertEquals(nepaliDate.month, (11)) //falgun
        Assert.assertEquals(nepaliDate.year, (2073))

        val englishDate: Calendar = NepaliDateUtils.bsToAd(2073, 11, 7)
        Assert.assertEquals(englishDate.get(Calendar.DAY_OF_MONTH), (18))
        Assert.assertEquals(englishDate.get(Calendar.MONTH), (1)) //feb
        Assert.assertEquals(englishDate.get(Calendar.YEAR), (2017))
    }

    @Test
    fun englishDateConvert() {
        val calendar: Calendar = GregorianCalendar(2017, 1, 18)
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(calendar)
        Assert.assertEquals(nepaliDate.day, (7))
        Assert.assertEquals(nepaliDate.month, (11)) //falgun
        Assert.assertEquals(nepaliDate.year, (2073))
    }

    @Test
    fun englishDate() {
        val nepaliDate = NepaliDate(2073, 11, 7) //falgun
        val englishDate: Calendar = NepaliDateUtils.bsToAd(nepaliDate)
        Assert.assertEquals(englishDate[Calendar.DAY_OF_MONTH], (18))
        Assert.assertEquals(englishDate[Calendar.MONTH], (1))
        Assert.assertEquals(englishDate[Calendar.YEAR], (2017))
    }

    @Test
    fun getFirstWeekDayMonth() {
        Assert.assertEquals(NepaliDateUtils.startWeekDayMonthMap.get(1980)[1], (6))
    }

    @Test
    fun noOfDaysInMonth() {
        Assert.assertEquals(NepaliDateUtils.daysInMonthMap.get(2090)[9], (29))
    }


    @Test
    fun getTodayNepaliDate() {
        val todayNepaliDate: NepaliDate = NepaliDateUtils.getInstance()
        val todayCalendar = Calendar.getInstance()
        val nepaliDate: NepaliDate = NepaliDateUtils.adToBs(todayCalendar)
        Assert.assertEquals(nepaliDate.day, (todayNepaliDate.day))
        Assert.assertEquals(nepaliDate.month, (todayNepaliDate.month))
        Assert.assertEquals(nepaliDate.year, (todayNepaliDate.year))
    }
}