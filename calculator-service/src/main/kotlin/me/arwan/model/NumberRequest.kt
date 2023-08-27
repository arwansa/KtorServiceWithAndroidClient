package me.arwan.model

data class NumbersRequest(
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