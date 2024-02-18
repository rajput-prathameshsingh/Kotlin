package com.example.unit_converter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unit_converter.ui.theme.Unit_ConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit_ConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Meter")}
    var outputUnit by remember{ mutableStateOf("Meter")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember{ mutableStateOf(false)}
    val conversionFactor = remember{ mutableStateOf(1.00)}
    val oconversionFactor = remember{ mutableStateOf(1.00)}



    fun convertUnit(){
        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Here all the UI elements will be stacked below each other.
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge )
        Spacer(modifier = Modifier.height(16.dp))
        // Here goes what should happen, when the value of our OutlinedField changes
            OutlinedTextField(inputValue,onValueChange = {
                inputValue = it
                convertUnit() },

                label = { Text("Enter value")}
            )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Input Box
            // Here all the UI elements will be stacked next to each other.
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meter"
                            conversionFactor.value = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeter") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeter"
                            conversionFactor.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }

                Spacer(modifier = Modifier.width(16.dp))
            // Output Box
            Box {
            // Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oconversionFactor.value = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meter"
                            oconversionFactor.value = 1.00
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeter"
                            oconversionFactor.value = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall )
        }

    }

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}

