import java.io.*
import java.util.*
import kotlin.math.min

fun getCount(n: Int, m: Int, i: Int, r: Int): Int{
    val width = n-(2*i)
    val height = m-(2*i)
    val count = (width+height)*2-4
    return r%count
}

// way - 0: down, 1: right, 2: up, 3: left
fun move(from: Pair<Int, Int>, count: Int, startWay: Int, min: Int, maxR: Int, maxC: Int): Pair<Int, Int>{
    var remain = count
    var way = startWay
    var curR = from.first
    var curC = from.second
    while (remain > 0){
        when(way){
            0 -> {
                if(curR+remain > maxR){
                    remain-=(maxR-curR)
                    curR = maxR
                    way = (way+1)%4
                }else{
                    curR+=remain
                    remain-=remain
                }
            }
            1 -> {
                if(curC+remain > maxC){
                    remain-=(maxC-curC)
                    curC = maxC
                    way = (way+1)%4
                }else{
                    curC+=remain
                    remain-=remain
                }
            }
            2 -> {
                if(curR-remain < min){
                    remain-=(curR-min)
                    curR = min
                    way = (way+1)%4
                }else{
                    curR-=remain
                    remain-=remain
                }
            }
            3 -> {
                if(curC-remain < min){
                    remain-=(curC-min)
                    curC = min
                    way = (way+1)%4
                }else{
                    curC-=remain
                    remain-=remain
                }
            }
        }
    }
    return Pair(curR, curC)
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m, r) = br.readLine().split(" ").map { it.toInt() }
    val input = Array(n){
        val st = StringTokenizer(br.readLine())
        Array(m){
            st.nextToken().toInt()
        }
    }

    val result : Array<Array<Int>> = Array(n){ Array(m){ -1 } }
    val range = min(n, m)/2
    for (i in 0 until range){
        val count = getCount(n, m, i, r)
        val min = i
        val maxR = n-i-1
        val maxC = m-i-1
        // start to down [row, min]
        for (row in min until maxR){
            val to = move(Pair(row, min), count, 0, min, maxR, maxC)
            result[to.first][to.second] = input[row][min]
        }
        // start to right [maxR, col]
        for(col in min until maxC){
            val to = move(Pair(maxR, col), count, 1, min, maxR, maxC)
            result[to.first][to.second] = input[maxR][col]
        }
        // start to up [row, maxC]
        for (row in maxR downTo min+1){
            val to = move(Pair(row, maxC), count, 2, min, maxR, maxC)
            result[to.first][to.second] = input[row][maxC]
        }
        // start to left [min, col]
        for (col in maxC downTo min+1){
            val to = move(Pair(min, col), count, 3, min, maxR, maxC)
            result[to.first][to.second] = input[min][col]
        }
    }

    result.forEach { row ->
        row.forEach {
            bw.write("$it ")
        }
        bw.newLine()
    }
    bw.flush()
    bw.close()
    br.close()
}
