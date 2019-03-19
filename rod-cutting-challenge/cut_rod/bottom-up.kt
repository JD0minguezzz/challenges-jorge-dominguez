import java.lang.Math.max

fun main(args: Array<String>) {
    val n = 10
    val prices = randomListBottomUp(n)
    println("\nn\t\tRuntime")
    val time = System.currentTimeMillis()
    bottomUpCutRod(prices, n)
    println("$n\t\t${System.currentTimeMillis()-time} ms")
    val n1 = 100
    val prices1 = randomListBottomUp(n1)
    val time1 = System.currentTimeMillis()
    bottomUpCutRod(prices1, n1)
    println("$n1\t\t${System.currentTimeMillis()-time1} ms")
    val n2 = 1000
    val prices2 = randomListBottomUp(n2)
    val time2 = System.currentTimeMillis()
    bottomUpCutRod(prices2, n2)
    println("$n2\t${System.currentTimeMillis()-time2} ms")
}


fun bottomUpCutRod(prices: List<Int>, n: Int): Int {
    val saved = IntArray(n + 1)
    saved[0] = 0
    for (j in 1..n) {
        var q = -1
        for (i in 0 until j) {
            q = max(q, prices[i] + saved[j - (i + 1)])
        }
        saved[j] = q
    }
    return saved[n]
}

fun randomListBottomUp(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}