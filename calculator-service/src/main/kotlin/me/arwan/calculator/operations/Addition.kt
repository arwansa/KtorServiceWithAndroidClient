package me.arwan.calculator.operations

import me.arwan.calculator.Operation

/**
 * Implements the addition operation.
 */
class Addition : Operation {
    override fun execute(vararg numbers: Long): Long {
        require(numbers.isNotEmpty()) { "At least one number is required." }
        return numbers.sum()
    }
}