package com.example.finanzapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val selectedDate = rememberDatePickerState()

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Seleccione una fecha")
            Spacer(modifier = Modifier.padding(8.dp))
            DatePicker(
                state = selectedDate
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = {
                val milisegundos = if(selectedDate.selectedDateMillis != null) selectedDate.selectedDateMillis!! + 86400000 else null
                onDateSelected(milisegundos)
                onDismiss()
            }) {
                Text(text = "Aceptar")
            }
        }
    }
}