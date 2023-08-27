package me.arwan.calculator.operations

import me.arwan.calculator.Operation

/**
 * Computes the remainder when the first number is divided by the sum of the subsequent numbers.
 */
class SplitNumber : Operation {
    override fun execute(vararg numbers: Long): Long {
        require(numbers.size > 1) { "At least two numbers are required." }
        val otherNumbersSum = numbers.drop(1).sum()
        require(otherNumbersSum != 0L) { "Other numbers sum must not be zero." }
        return numbers.first() % otherNumbersSum
    }
}