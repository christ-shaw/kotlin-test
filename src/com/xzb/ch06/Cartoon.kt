package com.xzb.ch06

class Cartoon(
    val firstName: String,
    val secondName: String,
    val email: Optional<String>
) {
    companion object {
        operator fun invoke(firstName: String, secondName: String, email: String? = null) =
            Cartoon(firstName, secondName, Optional(email))

    }

}

fun <k, v> Map<k, v>.getOptional(key: k): Optional<v> = Optional(this[key])
val f: (Cartoon) -> Optional<String> = fun(x: Cartoon) = x.email


fun mean(list: List<Double>): Optional<Double> =
    when {
        list.isEmpty() -> Optional()
        else -> Optional(list.sum() / list.size)
    }

fun <A,B> lift(f : (A) -> B) : (Optional<A>) -> Optional<B> ={ it -> it.map(f)}


fun main() {
    val toons: Map<String, Cartoon> = mapOf(
        "Mickey" to Cartoon("Mickey", "Mouse", "mickey@disney.com"),
        "Minnie" to Cartoon("Minne", secondName = "Mouse"),
        "Donald" to Cartoon("Donald", "Duck", "Donald@disney.com")
    )
    val optional = toons.getOptional("Mickey")
    // function variable
    println(optional.flatMap(f))
    // anonymous function
    println(optional.flatMap(fun(c: Cartoon) = c.email))
    // lambada statement
    println(optional.flatMap { it.email })
    // reference
    println(optional.flatMap(Cartoon::email))
    println(toons["Minnie"]?.email ?: "NoData")
    println(toons["Coofy"]?.email ?: "NoData")
}