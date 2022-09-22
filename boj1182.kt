import java.io.*
import java.util.*

var result = 0
fun dfs(length: Int, depth: Int, sum: Int, target: Int, arr: Array<Int>, start: Int){
    if(length == depth){
        if(sum==target) result++
        return
    }
    for(i in start..arr.lastIndex){
        dfs(length, depth+1, sum+arr[i], target, arr, i+1)
    }
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, s) = br.readLine().split(" ").map { it.toInt() }
    val st = StringTokenizer(br.readLine())
    val array = Array(n){
        st.nextToken().toInt()
    }
    array.sort()

    for(lenght in 1..n){
        dfs(lenght, 0, 0, s, array, 0)
    }
    bw.write("$result\n")
    bw.flush()

    bw.flush()
    bw.close()
    br.close()
}
