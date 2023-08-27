package me.arwan.calculator.operations

import me.arwan.calculator.Operation

/**
 * Splits a number into equally sized parts.
 */
class SplitEqually : Operation {
    override fun execute(vararg numbers: Long): String {
        require(numbers.size > 1) { "At least two numbers are required." }
        val (total, splitCount) = numbers
        require(splitCount != 0L) { "Split count must not be zero." }
        return List(splitCount.toInt()) { total / splitCount }.joinToString(
            prefix = "{",
            postfix = "}"
        )
    }
}