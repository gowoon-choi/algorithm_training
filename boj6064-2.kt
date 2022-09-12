import java.io.*
import kotlin.math.max
import kotlin.math.min

fun limit(m: Int, n: Int): Int{
    var a = max(m, n)
    var b = min(m, n)
    while (b!=0){
        val r = a%b
        a=b
        b=r
    }
    return a
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = br.readLine().toInt()
    for(test in 0 until t) {
        val (m, n, x, y) = br.readLine().split(" ").map { it.toInt() }
        val limit = m*n/limit(m, n)
        var flag = true
        var num = x
        while (num <= limit){
            if((num-1)%n+1 == y){
                bw.write("$num\n")
                bw.flush()
                flag = false
                break
            }
            num += m
        }
        if(flag){
            bw.write("-1\n")
            bw.flush()
        }
    }
    bw.close()
    br.close()
}
