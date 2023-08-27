package me.arwan.calculator.operations

import me.arwan.calculator.Operation

/**
 * Implements the division operation.
 */
class Division : Operation {
    override fun execute(vararg numbers: Long): Long {
        require(numbers.size > 1) { "At least two numbers are required." }
        val (dividend, divisor) = numbers
        require(divisor != 0L) { "Cannot divide by zero!" }
        return dividend / divisor
    }
}