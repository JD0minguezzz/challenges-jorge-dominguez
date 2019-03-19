import java.lang.Math.max

fun main(args: Array<String>) {
    val n = 100
//    val prices = listOf(1, 2, 5, 7, 9)
val prices = randomList(n)
    println(prices)
    val res = memoizeCutRod(prices, n)
    println(res)
}


fun memoizeCutRod(prices: List<Int>, n: Int): Int {
    val saved = IntArray(n + 1)
    for (i in 0..n) {
        saved[i] = -1
    }
    return memoizeCutRodAux(prices, n, saved)
}

fun memoizeCutRodAux(prices: List<Int>, n: Int, saved: IntArray): Int {
    if (saved[n] >= 0) return saved[n]
    var q = 0
    if (n != 0) {
        q = -1
        for (i in 0 until n) {
            q = max(q, prices[i] + memoizeCutRodAux(prices, n - (i+1), saved))
        }
    }
    saved[n] = q
    return q
}

fun randomList(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}