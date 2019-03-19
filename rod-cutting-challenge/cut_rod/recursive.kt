import kotlin.math.max

fun main(args: Array<String>) {
    val n = 10
    val prices = randomListRecursive(n)
    println("\nn\t\tRuntime")
    val time = System.currentTimeMillis()
    cutRod(prices, n)
    println("$n\t\t${System.currentTimeMillis()-time} ms")
    val n1 = 100
    val prices1 = randomListRecursive(n1)
    val time1 = System.currentTimeMillis()
    cutRod(prices1, n1)
    println("$n1\t\t${System.currentTimeMillis()-time1} ms")
    val n2 = 1000
    val prices2 = randomListRecursive(n2)
    val time2 = System.currentTimeMillis()
    cutRod(prices2, n2)
    println("$n2\t${System.currentTimeMillis()-time2} ms")
}


fun cutRod(prices: List<Int>, n: Int): Int {
    if (n == 0) return 0
    var revenue = -1
    for (i in 0 until n) {
        revenue = max(
            revenue,
            prices[i] + cutRod(prices, n - (i + 1))
        )
    }
    return revenue
}

fun randomListRecursive(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}