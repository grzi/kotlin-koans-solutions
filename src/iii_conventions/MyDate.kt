package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate>{
    override fun compareTo(other: MyDate): Int {
        val thisDate = ("" + year + (if(month<10) "0" else "") + month + (if(dayOfMonth<10) "0" else "") + dayOfMonth);
        val otherDate = ("" + other.year + (if(other.month<10) "0" else "") + other.month + (if(other.dayOfMonth<10) "0" else "") + other.dayOfMonth);
        return thisDate.toInt() - otherDate.toInt()
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate,override val endInclusive: MyDate): ClosedRange<MyDate>{
    override fun contains(value: MyDate): Boolean {
        return value >= start && value <= endInclusive
    }
}
