package me.arwan.calculator.operations

import me.arwan.calculator.Operation

/**
 * Implements the subtraction operation.
 */
class Subtraction : Operation {
    override fun execute(vararg numbers: Long): Long {
        require(numbers.isNotEmpty()) { "At least one number is required." }
        return numbers.reduce { acc, number -> acc - number }
    }
}