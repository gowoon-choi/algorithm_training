import java.io.*
import kotlin.math.E
import kotlin.math.max
import kotlin.math.min

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val balls = br.readLine()

    var SColor = balls[0]
    var EColor = balls[balls.lastIndex]

    var BCount = 0
    var RCount = 0
    for(ball in balls){
        if(ball == 'B') BCount++
        else RCount++
    }

    var SCount = 0
    for (ball in balls){
        if(ball == SColor) SCount++
        else break
    }

    var ECount = 0
    for (index in balls.lastIndex downTo 0){
        if(balls[index] == EColor) ECount++
        else break
    }

    var result: Int
    if(SColor == EColor){
        var target: Int
        var other: Int
        if(SColor == 'B'){
            target = BCount
            other = RCount
        }else{
            target = RCount
            other = BCount
        }
        result = min(other, target-max(SCount, ECount))
    }else{
        if(SColor == 'B'){
            result = min(BCount-SCount, RCount-ECount)
        }else{
            result = min(RCount-SCount, BCount-ECount)
        }
    }
    bw.write("$result\n")

    bw.flush()
    bw.close()
    br.close()
}
