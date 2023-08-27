package me.arwansa.calculatorapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.arwansa.calculatorapp.ui.components.NumberButton
import me.arwansa.calculatorapp.ui.theme.Purple40
import me.arwansa.calculatorapp.core.Resource
import me.arwansa.calculatorapp.data.model.OperatorEnum
import me.arwansa.calculatorapp.presentation.CalculatorViewModel

/**
 * Represents the main UI for the calculator application.
 *
 * @param viewModel The view model that handles business logic and provides data to the UI.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculatorUI(viewModel: CalculatorViewModel) {
    // Local state to hold user's numeric input.
    val input = remember { mutableStateOf("") }
    val resultState = viewModel.result.collectAsState()

    // Main layout column container.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Display area for the result of calculations.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.DarkGray)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            val resultValue = resultState.value
            val resultString = when (resultValue.status) {
                Resource.Status.IDLE -> "Result"
                Resource.Status.LOADING -> "Loading..."
                Resource.Status.SUCCESS -> {
                    val data = resultValue.data?.data
                    val result = if (data is Number) data.toLong() else data
                    "Result\n$result"
                }

                else -> "Error\n${resultValue.message.orEmpty()}"
            }
            Text(
                text = resultString,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display area for the input parameters/numbers.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.DarkGray)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Params\n${viewModel.inputNumbers.joinToString()}",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input field and add input button.
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1.8f)
                    .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
                    .padding(10.dp),
                contentAlignment = if (input.value.isBlank()) Alignment.Center else Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier,
                    text = input.value.ifBlank { "Input Number" }
                )
            }

            // Button to add the entered number to the input list.
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    input.value.toLongOrNull()?.let {
                        viewModel.run {
                            addInputNumber(it)
                            clearResult()
                        }
                        input.value = ""
                    }
                }) {
                Text("Add Input")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display number buttons for user input.
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            (listOf(1..9).flatten() + 0).forEach { num ->
                NumberButton(
                    text = num.toString(),
                    color = Purple40,
                    modifier = Modifier
                        .size(70.dp)
                        .clickable {
                            input.value += num.toString()
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Row for delete and backspace functionality.
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            // Button to clear all inputs.
            FilledIconButton(
                modifier = Modifier.size(64.dp),
                onClick = {
                    viewModel.clear()
                    input.value = ""
                }) {
                Icon(
                    Icons.Filled.Delete,
                    "contentDescription",
                )
            }

            // Button to remove the last entered number.
            FilledIconButton(
                modifier = Modifier.size(64.dp),
                onClick = {
                    viewModel.run {
                        removeLastInputNumber()
                        clearResult()
                    }
                }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    "contentDescription",
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Operator buttons to perform mathematical operations.
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OperatorEnum.values().forEach {
                Button(onClick = {
                    viewModel.postNumbers(it)
                }) {
                    Text(it.value)
                }
            }
        }
    }
}