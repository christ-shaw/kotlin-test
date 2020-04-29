fun main() {
//    val (slotNO,wheelNO,attempts) = readLine()!!.split(" ").map { it.toInt() }
//    val wheels = readLine()!!.split(" ").map { it.toInt() }.sorted()

    // 常规函数定义

    fun sum1(x: Int, y: Int): Unit {
        println(x * y)
    }

    fun sum2(x: Int, y: Int) = x + y


    var sum3 = fun(x: Int, y: Int): Int = x + y

    var sum4 = { x: Int, y: Int -> x + y }

    var sum5: (Int, Int) -> Int = { x, y -> x + y }

    println(sum3(1, 2))
    println(sum3.invoke(1, 2))


    var x: (Int, Int) -> Unit = fun(x: Int, y: Int) = println(x + y)

    x(19, 21)

    fun testFun(b: (Int, Int) -> Unit) {
        b(1, 2)
    }


    testFun(x)
    testFun { x, y -> println(x - y) }
    testFun(::sum1)


    fun doSomething(block: (Int, Int) -> String) {
       println(block(3, 5))
    }

    doSomething{x,y -> "{$x,$y}"}


    fun add(a:Int) : (Int) ->  Int {
        return { b:Int -> a + b}
    }

    val addOne = add(1)
    println(addOne::class.simpleName
    )
    println(add(1)(2))
}