import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val coins = Array(n){ readLine().toInt() }
    val dp = Array(k+1){ 0 }

    // init
    for(i in 0..k){
        if(i%coins[0] == 0) dp[i]++
    }

    // dp
    for(coinIdx in 1 until n){
        for(i in coins[coinIdx]..k){
            dp[i] += dp[i-coins[coinIdx]]
        }
    }

    println(dp[k])
}
