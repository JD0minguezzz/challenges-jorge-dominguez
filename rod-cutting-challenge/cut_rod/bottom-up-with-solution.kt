fun main(args: Array<String>) {
    val n = 10
    val prices = randomList(n)
    println("\nn\t\tRuntime")
    val time = System.currentTimeMillis()
    cutRodBottomUp(prices, n)
    println("$n\t\t${System.currentTimeMillis()-time} ms")
    val n1 = 100
    val prices1 = randomList(n1)
    val time1 = System.currentTimeMillis()
    cutRodBottomUp(prices1, n1)
    println("$n1\t\t${System.currentTimeMillis()-time1} ms")
    val n2 = 1000
    val prices2 = randomList(n2)
    val time2 = System.currentTimeMillis()
    cutRodBottomUp(prices2, n2)
    println("$n2\t${System.currentTimeMillis()-time2} ms")
}


fun cutRodBottomUp(prices: List<Int>, n: Int): Int {
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
    //println("sol: " + printSolution(n, solution))
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