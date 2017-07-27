package kotin.demo.com.myapplication

import android.support.annotation.IntRange

/**
 * Created by Kasper Tvede on 27-07-2017.
 */

data class MyIntRange1(@IntRange(from = 0) val value: Int)


fun tryMyIntRange1() {
    val missing = MyIntRange1(-1)
    //Missing error, about value beeing to low ?

    val usingAnInt = -2
    val missing2 = MyIntRange1(usingAnInt)
    //Missing error, about value beeing to low ?
}


data class MyIntRange2(@IntRange(from = 0, to = 450) val value: Int, @IntRange(from = 0) val value2: Int)

fun tryMyIntRange2() {
    val missing = MyIntRange2(-1, -10)
    //Missing error, about value beeing to low ? in both cases.
    val result = missing.value + missing.value2

    val missing2 = MyIntRange2(999999, 1)
    //Missing error, about value beeing to high ? in first cases.
}


@IntRange(from = -1, to = 10)
fun tryIntFromFunction(@IntRange(from = 0) inputInt: Int): Int {
    return 11 - inputInt //ehh this case fails, if we did not use the inputInt then it would do the right for a constant.
}


@IntRange(from = 0, to = 1)
fun tryIntFromFunction2(input: Boolean): Long {
    return if (input) {
        1
    } else {
        2 ///EHH it works in almost all other cases....
    }
}

@IntRange(from = 0, to = 1)
fun tryIntFromFunction3(input: Boolean?): Int {
    //this is completly also gone.. ? even if we move this to an expression
    return when {
        false -> 20
        true -> 1
        else -> -20
    }
}


@IntRange(from = 0, to = 1)
fun computeSomthing(): Long {
    return tryIntFromFunction2(true) //this is wrongly flagged......
}

fun randomInt(): Int {
    return 42
}

val constInt = 22

@IntRange(from = 0, to = 1)
fun computeSomthing2(): Long {
    return randomInt().toLong()  //this is not flagged ?
}

@IntRange(from = 0, to = 10)
fun computeSomthing3(): Int {
    return constInt  //this works but
}

val constInt2
    get() = 22 + 22

@IntRange(from = 0, to = 10)
fun computeSomthing4(): Int {
    return constInt2  //this does not work ?? (constInt2 is a constant of 44).
}

//one could easily repeat the tests  / example for double / floats .

