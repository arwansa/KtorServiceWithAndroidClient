package me.arwan.plugins

import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import me.arwan.calculator.Calculator
import me.arwan.enums.OperatorEnum.*
import me.arwan.model.ApiResponse
import me.arwan.model.NumbersRequest

fun Application.configureCalculatorRouting() {
    val calculator = Calculator()

    routing {
        post("/{mathematical-operation}") {
            try {
                val numbers = call.receive<NumbersRequest>().numbers.toLongArray()
                val result: Any = when (call.parameters["mathematical-operation"]) {
                    ADD.value -> calculator.add(*numbers)
                    SUBTRACT.value -> calculator.subtract(*numbers)
                    MULTIPLY.value -> calculator.multiply(*numbers)
                    DIVIDE.value -> calculator.divide(*numbers)
                    SPLIT_EQ.value -> calculator.splitEq(*numbers)
                    SPLIT_NUM.value -> calculator.splitNum(*numbers)
                    else -> ""
                }

                if (result != "") {
                    call.respond(ApiResponse(status = "success", data = result))
                } else {
                    call.respond(
                        ApiResponse(status = "error", data = null, error = "Invalid operations")
                    )
                }

            } catch (e: Exception) {
                call.respond(
                    ApiResponse(status = "error", data = null, error = e.localizedMessage)
                )
            }
        }
    }
}
