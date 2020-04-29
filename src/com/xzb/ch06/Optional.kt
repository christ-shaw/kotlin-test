package com.xzb.ch06

import java.lang.RuntimeException

sealed  class Optional<out T>
{
    abstract fun isEmpty():Boolean
    fun getOrElse(default: @UnsafeVariance T) : T
    {
        return when(this)
        {
            is None -> default
            is Some -> this.value
        }

    }
    fun <R> map(f : (T) -> R) : Optional<R>
    {
        return when(this)
        {
            is None -> None
            is Some -> Optional(f(this.value))
        }
    }

    fun <R> flatMap(f :(T)-> Optional<R>): Optional<R>
    {
       return map(f).getOrElse(None)
    }

//    fun <T>orElse(default: ()-> Optional<T>) : Optional<T>
//    {
//        return when(this)
//        {
//
//  lllhEh      }
//    }

    fun filter(p : (T)-> Boolean) : Optional<T>
    {
        return flatMap { x -> if(p(x)) this else None }
    }

    internal object None :  Optional<Nothing>()
    {
        override fun isEmpty(): Boolean {
            return true
        }

        override fun toString(): String {
            return "None"
        }

        override fun equals(other: Any?): Boolean {
            return other === None
        }

        override fun hashCode(): Int {
           return 0
        }
    }


    internal data class Some<out T> (internal val value : T) : Optional<T>()
    {
        override fun isEmpty(): Boolean {
            return  false
        }
    }
    companion object {
        operator  fun<T> invoke(a: T? = null) : Optional<T>
        {
            return when(a) {
                null -> None
                else -> Some(a)
            }
        }
    }
}

fun max(list: List<Int>) : Optional<Int>
{
    return Optional(list.max())
}
fun main()
{
    println(max(listOf(1, 2, 3)))
    println(max(listOf<Int>()))
//    println(max(listOf(1,2,3)).getOrElse(getDefault()));
    println(max(listOf(1, 2, 3).map { it * 2 }))

    listOf(1,2,3,5)
}

fun getDefault(): Int {
   throw RuntimeException()
}
