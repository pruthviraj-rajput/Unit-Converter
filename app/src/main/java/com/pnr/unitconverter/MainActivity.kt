 package com.pnr.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pnr.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitCoverter( )
                }
            }
        }
    }
}
@Composable
fun UnitCoverter(){
    var input by remember { mutableStateOf("")}
    var output by remember { mutableStateOf("") }
    var inputUnit by remember {mutableStateOf("METER") }
    var outputUnit by remember {mutableStateOf("METER")}
    var iExpandable by remember {mutableStateOf(false)}
    var oExpandable by remember {mutableStateOf(false)}
    val conversion = remember { mutableStateOf(1.00)}
    val Oconversion = remember { mutableStateOf(1.00)}


    fun letsConvert(){
        val inputFinal=input.toDoubleOrNull() ?:0.0
        val result=(inputFinal*conversion.value*100.0/Oconversion.value).roundToInt()/100.0
        output=result.toString()
    }
    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("UNIT CONVERTER", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value =input, onValueChange = { input=it }, label = { Text(text = "ENTER VALUE")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box {
                Button(onClick = {iExpandable=true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "SELECT AMONG THOSE")
                }
                DropdownMenu(expanded =iExpandable, onDismissRequest = {iExpandable=false}) {
                    DropdownMenuItem(text = { Text(text = "METER")},
                        onClick = {
                            iExpandable=false
                            inputUnit="METER"
                            conversion.value=1.00
                            letsConvert()
                        })
                    DropdownMenuItem(text = { Text(text = "CENTIMETER") },
                        onClick = {
                            iExpandable=false
                            inputUnit="CENTIMETER"
                            conversion.value=0.01
                            letsConvert()
                        })
                    DropdownMenuItem(text = { Text(text = "FEET")},
                        onClick = {
                            iExpandable=false
                            inputUnit="FEET"
                            conversion.value=0.3048
                            letsConvert()
                        })
                    DropdownMenuItem(text = { Text(text = "MILLIMETER") },
                        onClick = {
                            iExpandable=false
                            inputUnit="MILLIMETER"
                            conversion.value=0.001
                            letsConvert()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {oExpandable=true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop It Down")
                }
                DropdownMenu(expanded = oExpandable, onDismissRequest = {oExpandable=false}) {
                    DropdownMenuItem(text = { Text(text = "METER")},
                        onClick = {
                            oExpandable=false
                            outputUnit="METER"
                            Oconversion.value=1.00
                            letsConvert()
                        })
                    DropdownMenuItem(text = { Text(text = "CENTIMETER") },
                        onClick = {
                            oExpandable=false
                            outputUnit="CENTIMETER"
                            Oconversion.value=0.01
                            letsConvert()
                        })
                    DropdownMenuItem(text = { Text(text = "FEET")},
                        onClick = {
                            oExpandable=false
                            outputUnit="FEET"
                            Oconversion.value=0.3048
                            letsConvert()
                        })
                    DropdownMenuItem(text = { Text(text = "MILLIMETER") },
                        onClick = {
                            oExpandable=false
                            outputUnit="MILLIMETER"
                            Oconversion.value=0.001
                            letsConvert()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "RESULT is $output $outputUnit",style = MaterialTheme.typography.headlineLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitCoverter()
}