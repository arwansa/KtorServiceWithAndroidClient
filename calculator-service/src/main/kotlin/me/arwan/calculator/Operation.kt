package me.arwan.calculator

/**
 * Defines the basic contract for any mathematical operation.
 */
interface Operation {
    fun execute(vararg numbers: Long): Any
}