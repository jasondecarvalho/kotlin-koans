package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int = toIntMask(this).compareTo(toIntMask(other))

    private fun toIntMask(date: MyDate): Int =
            date.year * 10000 + date.month * 100 + date.dayOfMonth

    operator fun plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)

    operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate =
            addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.times)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(times: Int) = RepeatedTimeInterval(this, times)
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int) {

}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return DateRangeIterator(start, endInclusive)
    }

    override fun contains(date: MyDate): Boolean = start < date && date <= endInclusive

    class DateRangeIterator(var next: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
        override fun hasNext(): Boolean {
            return next <= endInclusive
        }

        override fun next(): MyDate {
            val current = next
            next = next.nextDay()
            return current
        }
    }
}
