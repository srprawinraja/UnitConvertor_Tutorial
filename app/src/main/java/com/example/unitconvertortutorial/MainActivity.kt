package com.example.unitconvertortutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertortutorial.ui.theme.UnitConvertorTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConvertorTutorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConvertor()
                }

            }
        }
    }
}

@Composable
fun unitConvertor() {
    var expandInp by remember { mutableStateOf(false) } // Tracks whether the input dropdown is expanded
    var expandOtp by remember { mutableStateOf(false) } // Tracks whether the output dropdown is expanded
    var inputValue by remember { mutableStateOf("") } // Stores the user's input value as a string
    var outputValue by remember { mutableStateOf("0.0") } // Stores the converted output value
    var inputUnit by remember { mutableStateOf("Meter") } // Tracks the selected input unit
    var outputUnit by remember { mutableStateOf("Meter") } // Tracks the selected output unit
    val inputConversionFactor = remember { mutableDoubleStateOf(1.0) } // Holds the conversion factor for the input unit
    val outputConversionFactor = remember { mutableDoubleStateOf(1.0) } // Holds the conversion factor for the output unit

    fun convert() {
        val inputValueDouble: Double = inputValue.toDoubleOrNull() ?: 0.0 // Convert inputValue to Double, defaulting to 0.0 if conversion fails
        val result = (inputValueDouble * inputConversionFactor.doubleValue) * outputConversionFactor.doubleValue // Calculate the converted result by multiplying the input value with the conversion factors
        outputValue = result.toString() // Convert the result to a string and assign it to outputValue for display
    }

    Column(
        modifier = Modifier.fillMaxSize(), // Fills the available space
        verticalArrangement = Arrangement.Center, // Centers content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers content horizontally
    ) {
        Text(
            text = "Result : $outputValue", // Displays the result value
            color = Color.Green, // Sets text color to green
            fontSize = 30.sp // Sets font size to 30sp
        )
        Spacer(modifier = Modifier.height(50.dp)) // Adds vertical space between elements

        OutlinedTextField(
            value = inputValue, // Binds the current input value
            onValueChange = {
                inputValue = it // Updates input value on change
            },
            label = { Text(text = "Enter The Value") } // Label for the text field
        )
        Spacer(modifier = Modifier.height(20.dp)) // Adds vertical space between elements

        Row {
            Row {
                // Box to contain the input unit button and dropdown menu
                Box {
                    // Button to trigger input unit dropdown menu
                    Button(onClick = {
                        expandInp = true // Set expandInp to true when clicked, to show the dropdown menu
                    }) {
                        Text(text = inputUnit) // Display the currently selected input unit
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "") // Arrow icon indicating dropdown functionality
                    }

                    // Dropdown menu for selecting input unit
                    DropdownMenu(expanded = expandInp, onDismissRequest = { expandInp = false }) {
                        // Dropdown items go here
// Dropdown items go here
                        DropdownMenuItem(text = { Text(text = "Meter") }, onClick = { // Menu item for "Meter"
                            inputUnit = "Meter" // Updates the input unit to "Meter"
                            inputConversionFactor.doubleValue = 1.0 // Sets the conversion factor for meters
                            expandInp = false // Hides the dropdown menu after selection
                        })
                        DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = { // Menu item for "Centimeter"
                            inputUnit = "Centimeter" // Updates the input unit to "Centimeter"
                            inputConversionFactor.doubleValue = 0.01 // Sets the conversion factor for centimeters
                            expandInp = false // Hides the dropdown menu after selection
                        })
                    }
                }

                Spacer(modifier = Modifier.width(16.dp)) // Adds horizontal space between the two buttons

                // Box to contain the output unit button and dropdown menu
                Box {
                    // Button to trigger output unit dropdown menu
                    Button(onClick = {
                        expandOtp = true // Set expandOtp to true when clicked, to show the dropdown menu
                    }) {
                        Text(text = outputUnit) // Display the currently selected output unit
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "") // Arrow icon indicating dropdown functionality
                    }

                    // Dropdown menu for selecting output unit
                    DropdownMenu(expanded = expandOtp, onDismissRequest = { expandOtp = false }) {
                        // Dropdown items go here
                        // Dropdown items go here
                        DropdownMenuItem(text = { Text(text = "Meter") }, onClick = { // Menu item for "Meter"
                            outputUnit = "Meter" // Updates the output unit to "Meter"
                            outputConversionFactor.doubleValue = 1.0 // Sets the conversion factor for meters
                            expandOtp = false // Hides the dropdown menu after selection
                        })
                        DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = { // Menu item for "Centimeter"
                            outputUnit = "Centimeter" // Updates the output unit to "Centimeter"
                            outputConversionFactor.doubleValue = 100.0 // Sets the conversion factor for centimeters
                            expandOtp = false // Hides the dropdown menu after selection
                        })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp)) // Adds vertical space of 10.dp between UI elements

        Button(onClick = {
            convert() // Calls the convert function when the button is clicked
        }) {
            Text(text = "Convert") // Displays the text "Convert" on the button
        }

    }

}




@Preview(showBackground = true)
@Composable
fun unitConvertorPreview() {
    UnitConvertorTutorialTheme {
        unitConvertor()
    }
}