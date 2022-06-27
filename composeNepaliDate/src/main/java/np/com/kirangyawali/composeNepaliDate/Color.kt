package np.com.kirangyawali.composeNepaliDate

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object NpDateColor {

    private val pickerBackgroundDarkTheme = Color(0xFF424242)
    private val pickerBackgroundLightTheme = Color(0xFFFFFFFF)

    private val textColorDarkTheme = Color(0xFFE7E7E7)
    private val textColorSelectedDarkTheme = Color(0xFFFFFFFF)
    private val textColorDisabledDarkTheme = Color(0xFF686767)

    private val textColorLightTheme = Color(0xFF000000)
    private val textColorSelectedLightTheme = Color(0xFFFFFFFF)
    private val textColorDisabledLightTheme = Color(0xFFCDCDCD)

    @Composable
    fun backgroundColor() = MaterialTheme.colors.surface


    @Composable
    fun textColor() = if (isSystemInDarkTheme()) textColorDarkTheme else textColorLightTheme


    @Composable
    fun selectedTextColor() =
        if (isSystemInDarkTheme()) textColorSelectedDarkTheme else textColorSelectedLightTheme


    @Composable
    fun disabledTextColor() =
        if (isSystemInDarkTheme()) textColorDisabledDarkTheme else textColorDisabledLightTheme

}
