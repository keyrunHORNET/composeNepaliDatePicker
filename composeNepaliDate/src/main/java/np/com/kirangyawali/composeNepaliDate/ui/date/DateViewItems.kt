package np.com.kirangyawali.composeNepaliDate.ui.date

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import np.com.kirangyawali.composeNepaliDate.NpDateColor
import np.com.kirangyawali.composeNepaliDate.NpDateColor.disabledTextColor
import np.com.kirangyawali.composeNepaliDate.NpDateColor.selectedTextColor
import np.com.kirangyawali.composeNepaliDate.NpDateColor.textColor
import np.com.kirangyawali.composeNepaliDate.R
import np.com.kirangyawali.composeNepaliDate.WEIGHT_DAY_VIEW_MONTH

@Composable
internal fun DayOfWeekViewItem(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 12.sp,
        color = textColor(),
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp / WEIGHT_DAY_VIEW_MONTH)
            .padding(top = 8.dp, bottom = 10.dp),
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
internal fun MonthViewIndicator(
    @DrawableRes
    imageId: Int,
    contentDescription: String,
    onClick: () -> Unit) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onClick() }
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp)
    )
}

@Composable
internal fun MonthDayViewItem(
    day: String,
    isSelected: Boolean,
    isEnabled: Boolean,
    isHighlight: Boolean,
    onSelect: (() -> Unit)? = null
) {
    Box {

        Text(
            text = day,
            textAlign = TextAlign.Center,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal,
            fontSize = 12.sp,
            color = if (isSelected) selectedTextColor()
            else if (isEnabled) textColor()
            else disabledTextColor(),
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp / WEIGHT_DAY_VIEW_MONTH)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = isEnabled && !isSelected
                ) { onSelect?.invoke() }
                .background(
                    color = if (isSelected) MaterialTheme.colors.primary
                    else NpDateColor.backgroundColor(),
                    shape = RoundedCornerShape(100)
                )
                .padding(top = 12.dp, bottom = 12.dp),
        )
        if (isHighlight) {
            Box(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = CircleShape
                    )
                    .align(Alignment.BottomCenter)
                    .size(4.dp)
            )
        }
    }
}


@Composable
internal fun YearViewItem(
    day: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Text(
        text = day,
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        color = if (isSelected) selectedTextColor() else textColor(),
        modifier = Modifier
            .background(
                if (isSelected) MaterialTheme.colors.primary
                else NpDateColor.backgroundColor(),
                shape = RoundedCornerShape(100)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) { onSelect.invoke() }
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
    )
}

@Composable
internal fun DialogButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        onClick = { onClick() },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button
        )
    }
}