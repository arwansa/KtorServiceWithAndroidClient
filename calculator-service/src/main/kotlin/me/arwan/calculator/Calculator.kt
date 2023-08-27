package me.arwan.calculator

/**
 * Calculator orchestrator that delegates mathematical operations.
 */
class Calculator {
    /**
     * Executes the specified operation with the provided numbers.
     * @param operation The operation to execute.
     * @param numbers The numbers on which the operation will be performed.
     * @return The result of the operation.
     */
    fun calculate(operation: Operation, vararg numbers: Long): Any = operation.execute(*numbers)
}