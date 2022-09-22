import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val t = readLine().toInt()
    for(test in 0 until t){
        val n = readLine().toInt()
        val stickers = Array(2){
            val st = StringTokenizer(readLine())
            Array(n) {
                st.nextToken().toInt()
        } }

        val dp = Array(2){ Array(n+1){ 0 } }
        dp[0][1] = stickers[0][0]
        dp[1][1] = stickers[1][0]

        for(i in 2..n){
            dp[0][i] = max(dp[1][i-1], dp[1][i-2]) + stickers[0][i-1]
            dp[1][i] = max(dp[0][i-1], dp[0][i-2]) + stickers[1][i-1]
        }
        println(max(dp[0][n], dp[1][n]))
    }
}
