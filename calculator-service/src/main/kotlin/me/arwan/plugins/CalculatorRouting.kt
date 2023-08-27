package me.arwan.plugins

import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.arwan.calculator.Calculator
import me.arwan.calculator.OperationType
import me.arwan.model.ApiResponse
import me.arwan.model.NumbersRequest

fun Application.configureCalculatorRouting() {
    val calculator = Calculator()

    routing {
        post("/{mathematical-operation}") {
            try {
                val numbers = call.receive<NumbersRequest>().numbers.toLongArray()
                val mathOperation = call.parameters["mathematical-operation"]
                val operation = OperationType.fromValue(mathOperation)?.operation?.invoke()
                val result = operation?.let { calculator.calculate(it, *numbers) }

                call.respond(
                    result?.let { ApiResponse(status = "success", data = it) }
                        ?: ApiResponse(status = "error", data = null, error = "Invalid operations")
                )
            } catch (e: Exception) {
                call.respond(
                    ApiResponse(status = "error", data = null, error = e.localizedMessage)
                )
            }
        }
    }
}
