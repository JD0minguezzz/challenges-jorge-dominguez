import java.lang.Math.max

fun main(args: Array<String>) {
    val n = 200
    //val prices = listOf(1, 2, 5, 7, 9)
    val prices = randomList(n)
    println(prices)
    val res = bottomUpCutRod(prices, n)
    println(res)
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

fun randomList(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}