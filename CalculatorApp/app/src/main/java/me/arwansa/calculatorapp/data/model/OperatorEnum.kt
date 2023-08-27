package me.arwansa.calculatorapp.data.model

/**
 * Enum representing the various mathematical operations
 * supported by the calculator app.
 *
 * Each operator is represented by its mathematical name and
 * an associated string value for display or other uses.
 */
enum class OperatorEnum(val value: String) {
    ADD("add"), // Represents the addition operation.
    SUBTRACT("subtract"), // Represents the subtraction operation.
    MULTIPLY("multiply"), // Represents the multiplication operation.
    DIVIDE("divide"), // Represents the division operation.
    SPLIT_EQ("splitEq"), // Represents the operation of splitting a number equally.
    SPLIT_NUM("splitNum"), // Represents the operation of splitting a number by modulus.
}