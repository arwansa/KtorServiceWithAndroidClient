package me.arwan.calculator

import me.arwan.calculator.operations.Addition
import me.arwan.calculator.operations.Division
import me.arwan.calculator.operations.Multiplication
import me.arwan.calculator.operations.SplitEqually
import me.arwan.calculator.operations.SplitNumber
import me.arwan.calculator.operations.Subtraction
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CalculatorTest {

    private lateinit var calculator: Calculator

    @BeforeTest
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun testAddition() {
        val addition = Addition()
        assertEquals(10L, calculator.calculate(addition, 1, 2, 3, 4))
        assertFailsWith<IllegalArgumentException> {
            calculator.calculate(addition)
        }
    }

    @Test
    fun testSubtraction() {
        val subtraction = Subtraction()
        assertEquals(-8L, calculator.calculate(subtraction, 1, 2, 3, 4))
        assertEquals(1L, calculator.calculate(subtraction, 1))
    }

    @Test
    fun testMultiplication() {
        val multiplication = Multiplication()
        assertEquals(24L, calculator.calculate(multiplication, 1, 2, 3, 4))
        assertEquals(1L, calculator.calculate(multiplication, 1))
    }

    @Test
    fun testDivision() {
        val division = Division()
        assertEquals(2L, calculator.calculate(division, 4, 2))
        assertFailsWith<IllegalArgumentException> {
            calculator.calculate(division, 4)
        }
        assertFailsWith<IllegalArgumentException> {
            calculator.calculate(division, 4, 0)
        }
    }

    @Test
    fun testSplitEqually() {
        val splitEqually = SplitEqually()
        assertEquals("{2, 2}", calculator.calculate(splitEqually, 4, 2))
        assertFailsWith<IllegalArgumentException> {
            calculator.calculate(splitEqually, 4)
        }
        assertFailsWith<IllegalArgumentException> {
            calculator.calculate(splitEqually, 4, 0)
        }
    }

    @Test
    fun testSplitNumber() {
        val splitNumber = SplitNumber()
        assertEquals(1L, calculator.calculate(splitNumber, 5, 2))
        assertFailsWith<IllegalArgumentException> {
            calculator.calculate(splitNumber, 5)
        }
    }
}