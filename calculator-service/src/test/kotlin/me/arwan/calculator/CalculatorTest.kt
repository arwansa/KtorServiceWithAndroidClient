package me.arwan.calculator
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
    fun testAdd() {
        assertEquals(15, calculator.add(1, 2, 3, 4, 5))
        assertFailsWith<IllegalArgumentException> { calculator.add() }
    }

    @Test
    fun testSubtract() {
        assertEquals(-13, calculator.subtract(1, 2, 3, 4, 5))
        assertEquals(10, calculator.subtract(10))
    }

    @Test
    fun testMultiply() {
        assertEquals(120, calculator.multiply(1, 2, 3, 4, 5))
        assertEquals(10, calculator.multiply(10))
    }

    @Test
    fun testDivide() {
        assertEquals(5, calculator.divide(10, 2))
    }

    @Test
    fun testDivideByZero() {
        assertFailsWith<IllegalArgumentException> { calculator.divide(10, 0) }
    }

    @Test
    fun testSplitEq() {
        assertEquals("{5, 5}", calculator.splitEq(10, 2))
    }

    @Test
    fun testSplitEqByZero() {
        assertFailsWith<IllegalArgumentException> { calculator.splitEq(10, 0) }
    }

    @Test
    fun testSplitNum() {
        assertEquals(40, calculator.splitNum(140, 45, 35, 20))
    }
}