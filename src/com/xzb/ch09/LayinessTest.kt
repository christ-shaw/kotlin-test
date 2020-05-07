package com.xzb.ch09

fun  main()
{
    val first : Boolean by lazy { true }
    val second : Boolean by lazy { throw IllegalStateException() }

    println( first || second)
    println( or(first , second))
}

fun or(first: Boolean, second: Boolean): Boolean {
    return if (first) first else second
}
