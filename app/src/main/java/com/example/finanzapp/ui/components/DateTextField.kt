package com.example.finanzapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.finanzapp.R
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    labelText: String,
    fechaString: String,
    onFechaSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }
    TextField(
        colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFFCFCFC)),
        modifier = Modifier
            .fillMaxWidth(),
        value = fechaString,
        onValueChange = { },
        shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        label = { Text(text = labelText) },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_calender),
                contentDescription = labelText,
                modifier = Modifier.clickable { showDialog = true }
            )
        },
        readOnly = true
    )

    if (showDialog) {
        AppDatePicker(
            onDateSelected = {
                if (it != null) {
                    selectedDate = Date(it)
                    onFechaSelected(SimpleDateFormat("dd-MM-yyyy").format(selectedDate))
                }
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}