package com.example.finanzapp.ui.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePicker(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val selectedDate = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val milisegundos = if(selectedDate.selectedDateMillis != null) selectedDate.selectedDateMillis!! + 86400000 else null
                    onDateSelected(milisegundos)
                    onDismiss()
                }
            ) {
                Text("Aceptar")
            }
        }
    ) {
        DatePicker(
            state = selectedDate
        )
    }
}