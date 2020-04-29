package com.xzb.`object`

// object Declaration
object Singleton {
    var x:String = "test"
    init {
        println("initialization object ")
    }

}

fun main(args: Array<String>) {
    var t = Thread{

         var s = Singleton
          s.x = "3"
    }.run();

    var t1 = Thread{
        var s = Singleton
        s.x = "5"
    }.run()

    print(Singleton.x)
}