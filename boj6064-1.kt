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
        val (m, n, x, y) = br.readLine().split(" ")
        val limit = m.toInt()*n.toInt()/limit(m.toInt(), n.toInt())

        val set = mutableListOf<Int>()
        var num = x.toInt()
        while(num <= limit){
            set.add(num)
            num += m.toInt()
        }

        var flag = true
        var index = 0
        num = y.toInt()
        while (num <= limit){
            for(i in index..set.lastIndex){
                if(set[i] > num){
                    index = i
                    break
                }
                if(set[i] == num){
                    bw.write("$num\n")
                    bw.flush()
                    flag = false
                    break
                }
            }
            if(!flag) break
            num += n.toInt()
        }
        if(flag){
            bw.write("-1\n")
            bw.flush()
        }
    }
    bw.close()
    br.close()
}
