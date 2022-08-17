import java.io.*
import java.util.*
import kotlin.math.abs

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = Array(n){ st.nextToken().toInt() }

    var left = 0
    var right = n-1
    var result = arr[left] + arr[right]
    var resultL = left
    var resultR = right
    var cur: Int
    // start from each side
    while(left < right){
        cur = arr[left]+arr[right]
        if(abs(cur) < abs(result)){
            result = abs(cur)
            resultL = left
            resultR = right
        }
        if(cur > 0){
            right--
        }else if(cur < 0){
            left++
        }else{
            break
        }
    }

    bw.write("${arr[resultL]} ${arr[resultR]}\n")
    bw.flush()
    bw.close()
    br.close()
}
