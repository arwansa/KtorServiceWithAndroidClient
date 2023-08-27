package me.arwansa.calculatorapp.data.model

import com.google.gson.annotations.SerializedName

data class NumbersRequest(
    @SerializedName("numbers")
    val numbers: Array<Long>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NumbersRequest

        if (!numbers.contentEquals(other.numbers)) return false

        return true
    }

    override fun hashCode(): Int {
        return numbers.contentHashCode()
    }
}