package me.arwansa.calculatorapp.data.repository

import me.arwansa.calculatorapp.data.model.NumbersRequest
import me.arwansa.calculatorapp.data.remote.CalculatorRemoteDataSource
import javax.inject.Inject

class CalculatorRepository @Inject constructor(
    private val remoteDataSource: CalculatorRemoteDataSource
) {

    suspend fun postNumbers(mathOperation: String, numbersRequest: NumbersRequest) =
        remoteDataSource.postNumbers(mathOperation, numbersRequest)
}