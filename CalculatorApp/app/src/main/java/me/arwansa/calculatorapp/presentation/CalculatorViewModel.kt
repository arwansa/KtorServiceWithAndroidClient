package me.arwansa.calculatorapp.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import me.arwansa.calculatorapp.core.launchSafeIO
import me.arwansa.calculatorapp.data.model.ApiResponse
import me.arwansa.calculatorapp.data.model.NumbersRequest
import me.arwansa.calculatorapp.data.model.OperatorEnum
import me.arwansa.calculatorapp.data.repository.CalculatorRepository
import me.arwansa.calculatorapp.core.Resource
import javax.inject.Inject


/**
 * ViewModel for the Calculator screen. Manages input numbers, operation results, and integrates with Calculator library.
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) : ViewModel() {

    /** A list maintaining the input numbers for operations. */
    val inputNumbers = mutableStateListOf<Long>()

    private var jobRequest: Job? = null

    private val _result = MutableStateFlow<Resource<ApiResponse<Any>>>(Resource.idle())
    val result = _result.asStateFlow()

    /**
     * Clears the input numbers list and result.
     */
    fun clear() {
        inputNumbers.clear()
        clearResult()
    }

    /**
     * Resets the result value to an empty string.
     */
    fun clearResult() {
        _result.value = Resource.idle()
    }

    /**
     * Adds a new number to the input numbers list.
     *
     * @param number The number to be added.
     */
    fun addInputNumber(number: Long) = inputNumbers.add(number)

    /**
     * Removes the last number from the input numbers list.
     */
    fun removeLastInputNumber() {
        if (inputNumbers.isNotEmpty()) {
            inputNumbers.removeLast()
        }
    }

    fun postNumbers(operator: OperatorEnum) {
        jobRequest?.cancel()
        jobRequest = launchSafeIO(
            blockBefore = {
                _result.value = Resource.loading()
            },
            blockIO = {
                val resource = calculatorRepository.postNumbers(
                    operator.value,
                    NumbersRequest(inputNumbers.toTypedArray())
                )
                withContext(Dispatchers.Main) {
                    _result.value = resource.data?.error?.let {
                        Resource.error(it)
                    } ?: resource
                }
            },
            blockException = {
                _result.value = Resource.error(it.localizedMessage.orEmpty())
            }
        )
    }

    override fun onCleared() {
        jobRequest?.cancel()
        super.onCleared()
    }
}