import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// 복 서 남 동 ( 왼쪽으로 회전 )
val dr = arrayOf(-1, 0, 1, 0)
val dc = arrayOf(0, -1, 0, 1)

// 0북 1동 2남 3서
val map = mapOf(
    0 to 0,
    1 to 3,
    2 to 2,
    3 to 1
)

// 후진 방향
val back = mapOf(
    0 to 2,
    1 to 3,
    2 to 0,
    3 to 1
)

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val (r, c, d) = readLine().split(" ").map { it.toInt() }
    val board = Array(n){
        val st = StringTokenizer(readLine())
        Array(m){
            st.nextToken().toInt()
    } }

    val visited = Array(n){ Array(m){ false } }
    var curR = r
    var curC = c
    var curD = map[d]!!
    var count = 0
    var flag = false
    while (true){
        if(!flag){
            flag = true
            visited[curR][curC] = true
            //1
            count++
        }
        //2-1, 2-2
        for (i in 0 until 4){
            val nextD = (curD+i+1)%4
            val nextR = curR+dr[nextD]
            val nextC = curC+dc[nextD]
            if(nextR in 0 until n && nextC in 0 until m && board[nextR][nextC] == 0 && !visited[nextR][nextC]){
                curR = nextR
                curC = nextC
                curD = nextD
                flag = false
                break
            }
        }
        // 2-2, 2-3
        if(flag){
            val backD = back[curD]!!
            val nextR = curR+dr[backD]
            val nextC = curC+dc[backD]
            if(nextR in 0 until n && nextC in 0 until m && board[nextR][nextC] == 0){
                curC = nextC
                curR = nextR
                continue

            }else{
                break
            }
        }
    }
    println(count)
}
