import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Point(
    val h: Int,
    val r: Int,
    val c: Int,
    val d: Int
)

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
    val dh = arrayOf(-1, 1, 0, 0, 0, 0)
    val dr = arrayOf(0, 0, -1, 1, 0, 0)
    val dc = arrayOf(0, 0, 0, 0, -1, 1)

    var remain = 0
    val queue = LinkedList<Point>()

    val (m, n, h) = readLine().split(" ").map { it.toInt() }
    val box = Array(h){ height ->
        Array(n){ row ->
            val st = StringTokenizer(readLine())
            Array(m){ col ->
                val state = st.nextToken().toInt()
                when(state){
                    0 -> remain++
                    1 -> queue.add(Point(height, row, col, 0))
                }
                state
            }
        }
    }

    var day = 0
    while (queue.isNotEmpty()){
        val cur = queue.poll()
        day = cur.d
        for (way in 0 until 6){
            val nh = cur.h + dh[way]
            val nr = cur.r + dr[way]
            val nc = cur.c + dc[way]
            if(nh in 0 until h && nr in 0 until n && nc in 0 until m){
                if(box[nh][nr][nc] == 0){
                    box[nh][nr][nc] = 1
                    remain--
                    queue.add(Point(nh, nr, nc, cur.d+1))
                }
            }
        }
    }
    if(remain > 0) println(-1)
    else println(day)
}
