package me.arwansa.calculatorapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import me.arwansa.calculatorapp.presentation.CalculatorViewModel
import me.arwansa.calculatorapp.ui.screens.CalculatorUI
import me.arwansa.calculatorapp.ui.theme.CalculatorAppTheme

@AndroidEntryPoint
class CalculatorActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorUI(viewModel)
                }
            }
        }
    }
}
