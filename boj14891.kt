import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

const val GEAR_COUNT = 8
const val WHEEL_COUNT = 4

fun revolve(gears: Array<List<Int>>, tops: Array<Int>, index: Int, direction: Int, visited: Array<Boolean>){
    visited[index] = true
    // 우측
    if(index < WHEEL_COUNT-1 && !visited[index+1]){
        val leftContactId = (tops[index]+2)%GEAR_COUNT
        val rightContactId = if(tops[index+1]-2 < 0) GEAR_COUNT+(tops[index+1]-2) else tops[index+1]-2
        if(gears[index][leftContactId] != gears[index+1][rightContactId]){
            revolve(gears, tops, index+1, direction*-1, visited)
        }
    }
    // 좌측
    if(index > 0 && !visited[index-1]){
        val leftContactId = (tops[index-1]+2)%GEAR_COUNT
        val rightContactId = if(tops[index]-2 < 0) GEAR_COUNT+(tops[index]-2) else tops[index]-2
        if(gears[index-1][leftContactId] != gears[index][rightContactId]){
            revolve(gears, tops, index-1, direction*-1, visited)
        }
    }

    when(direction){
        1 -> tops[index]--
        -1 -> tops[index]++
    }
    if(tops[index] < 0) tops[index] = GEAR_COUNT-1
    if(tops[index] >= GEAR_COUNT) tops[index] -= GEAR_COUNT
}

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val gears = Array(WHEEL_COUNT){
        readLine().toList().map { it-'0' }
    }
    val tops = Array(WHEEL_COUNT){ 0 }
    val n = readLine().toInt()
    for(i in 0 until n){
        val (id, dir) = readLine().split(" ").map { it.toInt() }
        revolve(gears, tops, id-1, dir, Array(4){false})
    }
    var result = 0
    for(i in gears.indices){
        if(gears[i][tops[i]]==1){
            result += 2.0.pow(i).toInt()
        }
    }
    println(result)
}
