package com.xzb.ch10

import java.lang.Integer.max

sealed class AbstractTree<out T : Comparable<@UnsafeVariance T>> {
    abstract fun isEmpty(): Boolean

    abstract fun size() : Int

    abstract fun height() : Int
    operator fun plus(a: @UnsafeVariance T): AbstractTree<T> = when (this) {
        Empty -> Tree(Empty, a, Empty)
        is Tree -> when {
            a < this.value -> Tree(left + a, this.value, right)
            a > this.value -> Tree(left, this.value, right + a)
            else -> Tree(this.left, a, this.right)
        }
    }


    internal object Empty : AbstractTree<Nothing>() {
        override fun isEmpty(): Boolean {
            return true
        }

        override fun toString(): String {
            return "Empty"
        }

        override fun size(): Int {
            return 0
        }

        override fun height(): Int {
            return -1
        }

    }

    internal class Tree<out T : Comparable<@UnsafeVariance T>>(
        internal val left: AbstractTree<T>,
        internal val value: T,
        internal val right: AbstractTree<T>
    ) : AbstractTree<T>() {
        override fun isEmpty(): Boolean {
            return false
        }

        override fun toString(): String {
            return "$left $value $right"
        }

        override fun size(): Int {
            return 1 + this.left.size() + this.right.size()
        }

        override fun height(): Int {
            return 1 + max(this.left.height(),this.right.height())
        }
    }

    fun contains(t: @UnsafeVariance T): Boolean {
        return when (this) {
            is Empty -> false
            is Tree -> when {
                t < this.value -> this.left.contains(t)
                t > this.value -> this.right.contains(t)
                else -> true
            }
        }
    }


    companion object {
        operator fun <T : Comparable<T>> invoke(): AbstractTree<T> = Empty
        operator fun <T : Comparable<T>> invoke(vararg az: T) =
            az.foldRight(Empty, { t: T, tree: AbstractTree<T> -> tree + t })

        operator fun <T : Comparable<T>> invoke(list: List<T>) =
            list.fold(Empty, {  tree: AbstractTree<T>,t:T -> tree + t })
    }


}


fun main() {
    val abstractTree = AbstractTree<Int>(listOf(8, 3, 4, 5, 13, 2, 4, 5, 2, 41, 3, 1))
    println(abstractTree)
    println(abstractTree.contains(1))
    println(abstractTree.contains(22))
    println(abstractTree.height() )
    println(abstractTree.size() )
}