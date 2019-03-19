import java.lang.Math.max

fun main(args: Array<String>) {
    val n = 10
    val prices = randomListTopDown(n)
    println("\nn\t\tRuntime")
    val time = System.currentTimeMillis()
    memoizeCutRod(prices, n)
    println("$n\t\t${System.currentTimeMillis()-time} ms")
    val n1 = 100
    val prices1 = randomListTopDown(n1)
    val time1 = System.currentTimeMillis()
    memoizeCutRod(prices1, n1)
    println("$n1\t\t${System.currentTimeMillis()-time1} ms")
    val n2 = 1000
    val prices2 = randomListTopDown(n2)
    val time2 = System.currentTimeMillis()
    memoizeCutRod(prices2, n2)
    println("$n2\t${System.currentTimeMillis()-time2} ms")
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

fun randomListTopDown(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}