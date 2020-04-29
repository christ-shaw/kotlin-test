
fun main()
{
    val times = readLine()!!.toInt()
    repeat(times)
    {
        val source = readLine()!!
        val target = readLine()!!
        if (analyze(target, source)) println("YES") else println("NO")
    }
}

private fun analyze(target: String,  source: String): Boolean {
    var iSource1 = 0
    for (iTarget in target.indices) {
        if (iSource1 >= source.length) {
           return false
        }
        if (target[iTarget] == source[iSource1]) {
            iSource1 += 1
            continue
        } else if (target[iTarget] == '-') {
            break
        } else {
            val offset = iSource1 + 1
            if (offset >= source.length) {
                return false
            } else {
                if (source[offset] != '-') {
                    return false
                } else {
                    iSource1 = offset + 1
                    continue
                }
            }
        }
    }
     if (iSource1 < source.length )
         return false
     return true
}