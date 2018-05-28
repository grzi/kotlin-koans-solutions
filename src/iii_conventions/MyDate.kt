package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate>{
    override fun compareTo(other: MyDate): Int {
        val thisDate = ("" + year + (if(month<10) "0" else "") + month + (if(dayOfMonth<10) "0" else "") + dayOfMonth);
        val otherDate = ("" + other.year + (if(other.month<10) "0" else "") + other.month + (if(other.dayOfMonth<10) "0" else "") + other.dayOfMonth);
        return thisDate.toInt() - otherDate.toInt()
    }


    operator fun rangeTo(other: MyDate): DateRange = DateRange(this, other)

    operator fun plus(time:TimeInterval) : MyDate {
       return this.addTimeIntervals(time,1);
    }

    operator fun plus(time:RepeatedTimeInterval) : MyDate{
        return this.addTimeIntervals(time.ti,time.n);
    }
}


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(n:Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

}


data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

class DateRange(override val start: MyDate,override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate>{
    override fun contains(value: MyDate): Boolean {
        return value >= start && value <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(var dateRange : DateRange) : Iterator<MyDate>{
    var current = dateRange.start

    override fun hasNext(): Boolean {
       return current <= dateRange.endInclusive
    }

    override fun next(): MyDate {
        val tmp = current
        current = current.nextDay()
        return tmp;
    }
}