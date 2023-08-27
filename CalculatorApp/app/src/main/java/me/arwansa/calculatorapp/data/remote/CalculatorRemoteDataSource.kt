package me.arwansa.calculatorapp.data.remote

import me.arwansa.calculatorapp.data.model.NumbersRequest
import javax.inject.Inject

class CalculatorRemoteDataSource @Inject constructor(
    private val calculatorService: CalculatorService
) : BaseDataSource() {

    suspend fun postNumbers(mathOperation: String, numbersRequest: NumbersRequest) =
        getResult { calculatorService.postNumbers(mathOperation, numbersRequest) }

}