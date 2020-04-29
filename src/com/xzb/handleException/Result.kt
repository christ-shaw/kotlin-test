package com.xzb.handleException

import com.xzb.A
import java.io.Serializable
import java.lang.IllegalStateException
import java.lang.NullPointerException

abstract class Result<out T> : Serializable {
    internal class Failure<out T>(private val exception: RuntimeException) : Result<T>() {
        override fun toString(): String {
            return "Failure(${exception.message}";
        }

        override fun <R> map(f: (T) -> R): Result<R> {
            return failure(exception)
        }

        override fun <R> flatMap(f: (T) -> Result<R>): Result<R> {
            return failure(exception)
        }
    }

    internal class Success<out T>(private val value: T) : Result<T>() {
        override fun toString(): String {
            return "success($value)"
        }

        override fun <R> map(f: (T) -> R): Result<R> {
            return try {
                Success(f(this.value))
            } catch (e: Exception) {
                failure(RuntimeException(e))
            } catch (e: RuntimeException) {
                failure(e)
            }

        }

        override fun <R> flatMap(f: (T) -> Result<R>): Result<R> {
            return try {
                f(this.value)
            } catch (e: Exception) {
                failure(RuntimeException(e))
            } catch (e: RuntimeException) {
                failure(e)
            }

        }

    }

    companion object {
        operator fun <T> invoke(a: T? = null): Result<T> = when (a) {
            null -> Failure(NullPointerException())
            else -> Success(a)
        }

        fun <T> failure(message: String): Result<T> = Failure(IllegalStateException(message))

        fun <T> failure(runtimeException: RuntimeException): Result<T> = Failure(runtimeException)

        fun <T> failure(exception: Exception): Result<T> = Failure(IllegalStateException(exception))
    }

    abstract fun <R> map(f: (T) -> R): Result<R>

    abstract fun <R> flatMap(f: (T) -> Result<R>): Result<R>

    fun filter(p:(T) -> Boolean ) : Result<T>
    {
        return flatMap { it-> if(p(it)) this else failure("unmatched") }
    }
    fun exists(p:(T) -> Boolean) = map(p).orElse(false)

    fun orElse(default: @UnsafeVariance T): Result<T>
    {
       return when(this)
       {
           is Success -> this
           else -> Result(default)
       }
    }

    fun orElse(default: () -> Result<@UnsafeVariance T>): Result<T> {
        return when (this) {
            is Success -> this
            else ->
                try {
                    default()

                } catch (ex: Exception) {
                    failure(ex)
                }
        }
    }
}