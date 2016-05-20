package iii_conventions

import util.TODO


class Invokable {
    var numberOfInvocations: Int = 0
    private set

    operator fun invoke(): Invokable {
        numberOfInvocations++
        return this;
    }
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().numberOfInvocations
}
