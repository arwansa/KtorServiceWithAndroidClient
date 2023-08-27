package me.arwan.calculator

import me.arwan.calculator.operations.Addition
import me.arwan.calculator.operations.Division
import me.arwan.calculator.operations.Multiplication
import me.arwan.calculator.operations.Subtraction
import me.arwan.calculator.operations.SplitEqually
import me.arwan.calculator.operations.SplitNumber

enum class OperationType(val value: String, val operation: () -> Operation) {
    ADD("add", { Addition() }),
    SUBTRACT("subtract", { Subtraction() }),
    MULTIPLY("multiply", { Multiplication() }),
    DIVIDE("divide", { Division() }),
    SPLIT_EQ("splitEq", { SplitEqually() }),
    SPLIT_NUM("splitNum", { SplitNumber() });

    companion object {
        fun fromValue(value: String?): OperationType? = values().find { it.value == value }
    }
}