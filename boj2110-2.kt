import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val houses = Array(n){ br.readLine().toInt() }
    houses.sort()

    var left = 1
    var right = houses[houses.lastIndex]-houses[0]
    var mid: Int
    while (left <= right){
        mid = (left+right)/2
        var count = 1
        var preIndex = 0
        for(index in 1..houses.lastIndex){
            if(houses[index]-houses[preIndex] >= mid){
                count++
                preIndex = index
            }
        }
        if(count>=c){
            left = mid+1
        }else if(count<c){
            right = mid-1
        }
    }
    bw.write("${(left+right)/2}\n")

    bw.flush()
    bw.close()
    br.close()
}
