import kotlin.math.max

fun main(args: Array<String>) {
    val time = System.currentTimeMillis()

    val n = 5
//    val prices = listOf(1,2,5,7,9)
    val prices = randomList(n)
    println(prices)
    val res = cutRod(prices, n)
    println(res)
    println("time: ${System.currentTimeMillis()-time} ms")
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

fun randomList(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    for (i in 1..n) {
        list.add((1 until n).random())
    }
    return list
}
