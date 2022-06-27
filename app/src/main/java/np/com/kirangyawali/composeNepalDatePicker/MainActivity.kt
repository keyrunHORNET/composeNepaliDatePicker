package np.com.kirangyawali.composeNepalDatePicker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import np.com.kirangyawali.composeNepalDatePicker.ui.theme.NepaliDatePickerComposeTheme
import np.com.kirangyawali.composeNepaliDate.NepaliDateConstants
import np.com.kirangyawali.composeNepaliDate.date.NepaliDate
import np.com.kirangyawali.composeNepaliDate.date.NepaliDateUtils
import np.com.kirangyawali.composeNepaliDate.ui.date.NepaliDatePicker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NepaliDatePickerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DatePicker()
                }
            }
        }
    }
}

@Composable
fun DatePicker() {

    // Date picker show/hide and onSelect
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<NepaliDate?>(null) }

    // Date picker options
    var showYearFirst by remember { mutableStateOf(true) }
    var minDate by remember { mutableStateOf<NepaliDate?>(null) }
    var maxDate by remember { mutableStateOf<NepaliDate?>(null) }
    var disableDays by remember { mutableStateOf<List<NepaliDate>?>(null) }
    var highlightDays by remember { mutableStateOf<List<NepaliDate>?>(null) }

    // conversion output
    var convertedDate by remember { mutableStateOf<NepaliDate?>(null) }
    var convertedDateError by remember { mutableStateOf<Exception?>(null) }

    // conversion input
    var day by remember { mutableStateOf(0) }
    var month by remember { mutableStateOf(0) }
    var year by remember { mutableStateOf(0) }

    if (showDialog) {
        NepaliDatePicker(
            minDate = minDate,
            maxDate = maxDate,
            disableDays = disableDays,
            showYearPickerFirst = showYearFirst,
            highlightDays = highlightDays,
            onDateSelected = {
                selectedDate = it
            }, onDismiss = {
                showDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Conversion", style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            UserInputDate(
                label = "year (${NepaliDateConstants.minNepYear}-${NepaliDateConstants.maxNepYear})",
                text = year,
                onChange = {
                    year = it
                })
            UserInputDate(
                label = "month (${NepaliDateConstants.minNepMonth}-${NepaliDateConstants.maxNepMonth})",
                length = 2,
                text = month,
                onChange = {
                    month = it
                })
            UserInputDate(
                label = "day (${NepaliDateConstants.minNepDay}-${NepaliDateConstants.maxNepDay})",
                length = 2,
                text = day,
                onChange = {
                    day = it
                })
        }

        convertedDateError?.let {
            Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_SHORT).show()
            convertedDateError = null //reset
        }

        Button(
            enabled = day != 0 && year != 0 && month != 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), onClick = {
                try {
                    convertedDate = NepaliDateUtils.adToBs(year, month, day)
                } catch (e: IllegalArgumentException) {
                    convertedDateError = e
                }
            }) {
            convertedDate?.let {
                Text(text = " ${it.year}/${it.month}/${it.day} BS")
            } ?: Text(text = "convert AD to BS")
        }


        Text(
            text = "Date picker", style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        )


        DatePickerOptions(title = "Set min date", checkedState = minDate != null) {
            minDate = if (it) NepaliDate(year = 2078, month = 11, day = 6) else null
        }

        minDate?.let { date ->
            Text(
                modifier = Modifier.padding(start = 70.dp),
                text = "${date.year}/${date.month}/${date.day}",
                style = MaterialTheme.typography.caption
            )
        }

        DatePickerOptions(title = "Set max date", checkedState = maxDate != null) {
            maxDate = if (it) NepaliDate(year = 2079, month = 6, day = 10) else null
        }
        maxDate?.let { date ->
            Text(
                modifier = Modifier.padding(start = 70.dp),
                text = "${date.year}/${date.month}/${date.day}",
                style = MaterialTheme.typography.caption
            )
        }

        DatePickerOptions(title = "show year picker first", checkedState = showYearFirst) {
            showYearFirst = it
        }

        DatePickerOptions(title = "disable date", checkedState = disableDays != null) {
            disableDays = if (it) listOf(
                NepaliDate(year = 2079, month = 2, day = 11),
                NepaliDate(year = 2079, month = 2, day = 12),
                NepaliDate(year = 2079, month = 3, day = 12),
                NepaliDate(year = 2079, month = 3, day = 13),
                NepaliDate(year = 2079, month = 3, day = 23),
                NepaliDate(year = 2079, month = 3, day = 24),
                NepaliDate(year = 2079, month = 3, day = 25),
                NepaliDate(year = 2079, month = 4, day = 9),
                NepaliDate(year = 2079, month = 4, day = 1),
            ) else null
        }

        disableDays?.let { list ->
            Column(modifier = Modifier.padding(start = 70.dp)) {
                list.forEach { date ->
                    Text(
                        text = "${date.year}/${date.month}/${date.day}",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }

        DatePickerOptions(title = "highlight date", checkedState = highlightDays != null) {
            highlightDays = if (it) listOf(
                NepaliDate(year = 2079, month = 2, day = 10),
                NepaliDate(year = 2079, month = 2, day = 12),
                NepaliDate(year = 2079, month = 3, day = 10),
                NepaliDate(year = 2079, month = 3, day = 13),
                NepaliDate(year = 2079, month = 3, day = 14),
                NepaliDate(year = 2079, month = 3, day = 15),
                NepaliDate(year = 2079, month = 4, day = 16),
                NepaliDate(year = 2079, month = 4, day = 1),
            ) else null
        }

        highlightDays?.let { list ->
            Column(modifier = Modifier.padding(start = 70.dp)) {
                list.forEach { date ->
                    Text(
                        text = "${date.year}/${date.month}/${date.day}",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                showDialog = !showDialog
            }
        ) {
            selectedDate?.let {
                Text(text = "selected date: ${it.year}/${it.month}/${it.day}")
            } ?: Text(text = "show Nepali Date picker")
        }

    }
}

@Composable
private fun DatePickerOptions(
    title: String,
    checkedState: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { onCheckedChanged(it) }
        )
        Text(text = title, modifier = Modifier.padding(start = 16.dp))
    }
}

@Composable
private fun UserInputDate(
    label: String,
    text: Int,
    length: Int = 4,
    onChange: (Int) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(4.dp)
            .width((120).dp),
        value = text.toString(),
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            if (it.length <= length && it.isNotEmpty()) {
                onChange(it.toInt())
            }
        }
    )
}