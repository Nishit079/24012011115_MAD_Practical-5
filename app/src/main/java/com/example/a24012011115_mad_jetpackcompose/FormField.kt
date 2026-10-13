package com.example.a24012011115_mad_jetpackcompose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormField(
    label: String,
    textState: String,
    onTextChange: (String) -> Unit,
    isPasswordField: Boolean = false,
    isNumber: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        val fieldModifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)

        if (!isNumber) {
            OutlinedTextField(
                value = textState,
                onValueChange = onTextChange,
                modifier = fieldModifier,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                visualTransformation = if (isPasswordField)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (isPasswordField) KeyboardType.Password else KeyboardType.Text
                ),
                label = { Text("Enter $label") },
                placeholder = { Text("Enter $label") }
            )
        } else {
            NumericOutlinedTextField(
                label = label,
                value = textState,
                modifier = fieldModifier,
                onTextChange = onTextChange
            )
        }
    }
}

@Composable
fun NumericOutlinedTextField(
    label: String,
    value: String,
    modifier: Modifier,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            if (newText.all { it.isDigit() }) {
                onTextChange(newText)
            }
        },
        modifier = modifier,
        singleLine = true,
        label = { Text("Enter $label") },
        textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
        placeholder = { Text("Enter $label") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = VisualTransformation.None
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        FormField("Android", "", {})
    }
}
