import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val arr = Array(n+1){ 0 }
    val st = StringTokenizer(readLine())
    for(i in 1..n){
        arr[i] = st.nextToken().toInt()
    }
    val dp = Array(n+1){ -1 }
    dp[0] = 0
    var max = 0
    for(i in 1..n){
        for(j in 0 until i){
            if(arr[j] < arr[i] && dp[j] > dp[i]){
                dp[i] = dp[j]
            }
        }
        dp[i]++
        if(dp[i] > max) max = dp[i]
    }
    println(max)
}
