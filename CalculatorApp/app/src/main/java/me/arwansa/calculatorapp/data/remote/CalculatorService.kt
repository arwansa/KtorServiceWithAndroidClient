package me.arwansa.calculatorapp.data.remote

import me.arwansa.calculatorapp.data.model.ApiResponse
import me.arwansa.calculatorapp.data.model.NumbersRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CalculatorService {

    @POST("{math-operation}")
    suspend fun postNumbers(
        @Path("math-operation") mathOperation: String,
        @Body numbersRequest: NumbersRequest
    ): Response<ApiResponse<Any>>
}