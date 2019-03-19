fun main(args: Array<String>) {
    val n = 5
    val prices = listOf(10, 2, 5, 7, 9)
    //val prices = randomList(n)
    println(prices)
    val res = bottomUpCutRod(prices, n)
    println(res)
}


fun bottomUpCutRod(prices: List<Int>, n: Int): Int {
    val saved = IntArray(n + 1)
    val solution = IntArray(n + 1)
    saved[0] = 0
    for (j in 1..n) {
        var q = -1
        for (i in 0 until j) {
            if (q < prices[i] + saved[j - (i + 1)]) {
                q = prices[i] + saved[j - (i + 1)]
                solution[j] = (i + 1)
            }

        }
        saved[j] = q
    }
    println("sol: " + printSolution(n, solution))
    return saved[n]
}

fun printSolution(pN: Int, solution: IntArray): String {
    var n = pN
    var r = ""
    while (n > 0) {
        r += "${solution[n]},"
        n -= (solution[n])
    }
    return r
}


fun randomList(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}