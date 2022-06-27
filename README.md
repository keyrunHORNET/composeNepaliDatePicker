# Nepali Date picker Converter - Re in Compose

 This is a re-work of [Nepali Date Picker Converter](https://github.com/keyrunHORNET/date_picker_converter) in jetpack compose.
 
## Using Date Picker

```kotlin
var showDialog by remember { mutableStateOf(false) }

if (showDialog) {
    NepaliDatePicker(
        onDateSelected = { date ->
            // selected date 
        },
      onDismiss = {
            showDialog = false
        }
    ) 
}
```

`NepaliDate` is a data class used to hold value of year, month, day and weekday in date converters and in callbacks of date picker.

`onDismiss` is mandatory for callback on dismiss of picker dialog.

`onDateSelected` is mandatory for callback on date selected. Returns selected`NepaliDate` 

### Picker Options

`NepaliDatePicker` can take additional arguments to customise picker to your needs

* `startDate` as `NepaliDate` for the date you want to start with, in picker. Its current instance of date by default.

* `showYearPickerFirst` as `Boolean` to show year picker first when picker is displayed. Its `true` by default and set it to false if you want month view as default view. Especially if you have limited date for selection in picker.

* `minDate` as `NepaliDate` to set the lower limit of date in picker.

* `maxDate` as `NepaliDate` to set upper limit of date in picker.

* `highlightDays` as `List<NepaliDate>`. This will highlight the given list of dates in picker.

* `disableDays` as `List<NepaliDate>`. This will disable selection of the given dates in picker.

## Using Date Converter

value when passed beyond the conversion range throws an `IllegalArgumentException`. Make sure you catch them.

* Converting english Date to Nepali date (i.e A.D to B.S):
```kotlin
NepaliDateUtils.adToBs(nepYY,nepMM,nepDD)
```
you can also pass the calendar instance as an argument

* Converting Nepali Date to English date (i.e B.S to A.D):
```kotlin
NepaliDateUtils.bsToAd(nepYY,nepMM,nepDD)
```
you can also pass NepaliDate as an argument


### Additional Options

Accessible from `NepaliDateUtils`

* `NepaliDateUtils.isEngDateInRange(int yy,int mm,int dd)` returns `true` if english date is within the range of conversion.

* `NepaliDateUtils.isNepDateInRange(int yy,int mm,int dd)` returns `true` if nepali date is within the range of conversion.

* `getInstance()` public method of class `DateConverter` returns Nepali Date Model   

* `getWeekDay(int nepYY, int nepMM, int nepDD)` public method of class `DateConverter` returns `int` [1-7] representing seven days of week

## License

MIT License

Copyright (c) 2022 Kiran Gyawali

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.