package com.example.taskorganizaer.ui.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskorganizaer.ui.presentation.updateTaskScreen.UpdateTaskViewModel
import java.time.LocalDate
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.marosseleng.compose.material3.datetimepickers.time.ui.dialog.TimePickerDialog
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DeadlinePickerDialog(
    dismissDialog: () -> Unit,
    updateDeadline: (LocalDateTime?) -> Unit,
    deadlineDate: LocalDate? = null,
    deadlineTime: LocalTime? = null
) {
    var date by remember { mutableStateOf(deadlineDate)}
    var time by remember { mutableStateOf(deadlineTime)}
    var showDatePicker by remember { mutableStateOf(false)}
    var showTimePicker by remember { mutableStateOf(false)}
    AlertDialog(
        onDismissRequest = { dismissDialog() },
        title = { Text(text = "Task deadline")},
        text = {
            Column() {
                Row() {
                    Button(
                        onClick = {
                            showDatePicker = true
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp),

                        ) {
                        Text(text = if(date != null) {
                            "Date: ${date!!.month} ${date!!.dayOfMonth}, ${date!!.year}"
                        }
                        else {
                            "Pick a date"
                        }
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .padding(top=8.dp),
                        onClick = {
                            date = null
                    }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "delete selected date deadline")
                    }

                }
                Row() {
                    Button(
                        onClick = {
                            showTimePicker = true
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp),

                        ) {
                        Text(text = if(time != null) {
                            "Time: ${time!!.hour}:${time!!.minute}"
                        }
                        else {
                            "Pick a time"
                        }
                        )

                    }
                    IconButton(
                        modifier = Modifier
                            .padding(top=8.dp),
                        onClick = {
                            time = null
                    }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "delete selected time deadline")
                    }
                }

            }
            if(showDatePicker){
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    onDateChange = {
                        date = it
                        showDatePicker = false
                    }
                )
            }
            if(showTimePicker){
                TimePickerDialog(
                    onDismissRequest = { showTimePicker = false},
                    onTimeChange = {
                        time = it
                        showTimePicker = false
                    })
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Column (
                    horizontalAlignment = Alignment.End
                ) {

                    Button(
                        onClick = {
                            if(date == null && time == null){
                                updateDeadline(null)
                                dismissDialog()
                            }
                            else if(date == null && time != null){
                                date = LocalDate.now()
                                val pickedDeadline = LocalDateTime.of(
                                    date,time
                                )
//                                updateTaskViewModel.updateDeadline(pickedDeadline)
                                updateDeadline(pickedDeadline)
                                dismissDialog()
                            }
                            else if (date != null && time == null){
                                time = LocalTime.of(0, 0)
                                val pickedDeadline = LocalDateTime.of(
                                    date,time
                                )
//                                updateTaskViewModel.updateDeadline(pickedDeadline)
                                updateDeadline(pickedDeadline)
                                dismissDialog()
                            }
                            else {
                                val pickedDeadline = LocalDateTime.of(
                                    date,time
                                )
//                                updateTaskViewModel.updateDeadline(pickedDeadline)
                                updateDeadline(pickedDeadline)
                                dismissDialog()
                            }
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                    ) {
                        Text(text = "Save")
                    }
                }

            }
        }
    )
}