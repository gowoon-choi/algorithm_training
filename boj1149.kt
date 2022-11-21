import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val rgbArr = Array(3){ 0 }
    for (i in 0 until n){
        var (r, g, b) = readLine().split(" ").map { it.toInt() }
        r += min(rgbArr[1], rgbArr[2])
        g += min(rgbArr[0], rgbArr[2])
        b += min(rgbArr[1], rgbArr[0])
        rgbArr[0] = r
        rgbArr[1] = g
        rgbArr[2] = b
    }
    rgbArr.sort()
    println(rgbArr[0])
}
