package me.arwan.calculator

class Calculator {

    /**
     * Add all provided numbers.
     *
     * This function takes a variable number of long arguments and returns their sum.
     * If no numbers are provided, an exception is thrown.
     *
     * @param numbers Varargs of longs to be summed.
     * @throws IllegalArgumentException If no numbers are provided.
     * @return The total sum of all provided numbers.
     */
    fun add(vararg numbers: Long): Long {
        require(numbers.isNotEmpty()) { "At least one number is required." }
        return numbers.sum()
    }

    /**
     * Subtract numbers in sequence.
     *
     * This function subtracts each subsequent number from the accumulated result.
     * If no numbers are provided, an exception is thrown.
     *
     * @param numbers Varargs of longs to be subtracted.
     * @throws IllegalArgumentException If no numbers are provided.
     * @return Result after performing the subtraction in sequence.
     */
    fun subtract(vararg numbers: Long): Long {
        require(numbers.isNotEmpty()) { "At least one number is required." }
        return numbers.reduce { acc, number -> acc - number }
    }

    /**
     * Multiplies all provided numbers.
     *
     * This function takes a variable number of long arguments and returns the result of multiplying them together.
     * If no numbers are provided, an exception is thrown.
     *
     * @param numbers Varargs of longs to be multiplied.
     * @throws IllegalArgumentException If no numbers are provided.
     * @return Result of multiplying all input longs.
     */
    fun multiply(vararg numbers: Long): Long {
        require(numbers.isNotEmpty()) { "At least one number is required." }
        return numbers.reduce { acc, number -> acc * number }
    }

    /**
     * Divides the first number by the second.
     *
     * This function takes in a variable number of longs and returns the result of dividing
     * the first number by the second number. If less than two numbers are provided or if the
     * divisor is zero, an exception is thrown.
     *
     * Note: Only the first two numbers are used for division, even if more are provided.
     *
     * @param numbers The first number acts as the dividend and the second as the divisor.
     * @throws IllegalArgumentException If less than two numbers are provided or if an attempt is made to divide by zero.
     * @return The result of dividing the first number by the second number.
     */
    fun divide(vararg numbers: Long): Long {
        require(numbers.size > 1) { "At least two numbers are required." }
        val (dividend, divisor) = numbers
        require(divisor != 0L) { "Cannot divide by zero!" }
        return dividend / divisor
    }

    /**
     * Divides the first number equally based on the value of the second number.
     *
     * This function aims to split the first provided number into several equal parts, as specified by the second number.
     * If the second number (split count) is zero, an exception will be thrown.
     *
     * @param numbers The first number represents the value to be divided, and the second number specifies into how many equal parts it should be divided.
     * @throws IllegalArgumentException If less than two numbers are provided or if the split count is zero.
     * @return A string representing equal divisions of the first number in a custom format.
     */
    fun splitEq(vararg numbers: Long): String {
        require(numbers.size > 1) { "At least two numbers are required." }
        val (total, splitCount) = numbers
        require(splitCount != 0L) { "Split count must not be zero." }
        return List(splitCount.toInt()) { total / splitCount }.toCustomString()
    }

    /**
     * Computes the remainder when the first number is divided by the sum of all the subsequent numbers.
     *
     * This function takes the first number and calculates the modulus with the sum of all the
     * subsequent numbers in the varargs list. For example, if the input is 140, 45, 35, 20,
     * the function calculates 140 % (45 + 35 + 20) = 40.
     *
     * @param numbers The first number acts as the dividend, while the sum of all subsequent numbers acts as the divisor.
     * @throws IllegalArgumentException If less than two numbers are provided.
     * @return The remainder after dividing the first number by the sum of the subsequent numbers.
     */
    fun splitNum(vararg numbers: Long): Long {
        require(numbers.size > 1) { "At least two numbers are required." }
        val otherNumbersSum = numbers.drop(1).sum()
        require(otherNumbersSum != 0L) { "Other numbers sum must not be zero." }
        return numbers.first() % otherNumbersSum
    }

    private fun List<Long>.toCustomString(): String {
        return joinToString(prefix = "{", postfix = "}")
    }
}