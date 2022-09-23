import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val str1 = readLine()
    val str2 = readLine()
    val arr = Array(str1.length+1){ Array(str2.length+1){ 0 } }
    for(i in 1..str1.length){
        for(j in 1..str2.length){
            if(str1[i-1]==str2[j-1]){
                arr[i][j] = arr[i-1][j-1]+1
            }else{
                arr[i][j] = max(arr[i-1][j], arr[i][j-1])
            }
        }
    }
    println(arr[str1.length][str2.length])
}
