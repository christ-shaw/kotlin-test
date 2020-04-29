package com.xzb.handleException

sealed class Either<out A,out B>
{
     internal class Left<out A, out B>(val  value:A) : Either<A,B>()
     {
          override fun toString(): String {
              return "Left($value)"
          }
     }

internal class Right<out A, out B>(val value: B): Either<A,B>()
     {
          override fun toString(): String {
              return "Rigth($value)"
          }
     }

     companion object
     {
          fun<A,B> left(value : A):Either<A,B> = left(value)
          fun<A,B> right(value : A):Either<A,B> = right(value)
     }
}