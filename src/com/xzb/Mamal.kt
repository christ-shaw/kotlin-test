package com.xzb

abstract class Mammal {
    abstract fun eat();
}

class Sloth() : Mammal()
{
    init {
        println("sloth  built")
    }

    override fun eat() {
        println("sloth eats")
    }

}

class Panda() : Mammal()
{
    init {
        println("panda  built")
    }

    override fun eat() {
        println("panda eats")
    }

}

fun  feed(animals : List<Mammal>)
{
    animals.forEach{it.eat()}
}

fun <T:Mammal> List<T>.feedCrew() = this.forEach{it.eat()}

open class A()
class B:  A()

class Holder<out T> (val obj: T)

fun main()
{

var mammals = listOf(Sloth(),Panda())
feed(mammals)

    mammals.feedCrew()

    var holderA : Holder<A> = Holder(B())
}


