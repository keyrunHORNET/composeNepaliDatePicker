package np.com.kirangyawali.composeNepaliDate

import np.com.kirangyawali.composeNepaliDate.date.NepaliDate

internal const val WEIGHT_DAY_VIEW_MONTH = 10

object NepaliDateConstants {
    const val minNepYear = 1970
    const val minNepMonth = 1
    const val minNepDay = 1

    const val maxNepYear = 2090
    const val maxNepMonth = 12
    const val maxNepDay = 30

    const val minEngYear = 1913
    const val minEngMonth = 1
    const val minEngDay = 1

    const val maxEngYear = 2033
    const val maxEngMonth = 12
    const val maxEngDay = 31

    val minNepaliDate = NepaliDate(minNepYear, minNepMonth, minNepDay)
    val maxNepaliDate = NepaliDate(maxNepYear, maxNepMonth, maxNepDay)

}