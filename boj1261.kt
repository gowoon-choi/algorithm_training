import java.io.*
import java.util.*

val dr = arrayOf(0, 0, 1, -1)
val dc = arrayOf(1, -1 ,0, 0)

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(m){
        val row = br.readLine()
        Array(n){
            row[it]-'0'
        }
    }

    val visited = Array(m){ Array(n){ -1 } }
    visited[0][0] = 0
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, 0))

    while (queue.isNotEmpty()){
        val cur = queue.poll()
        for(i in 0..3){
            val nextR = cur.first+dr[i]
            val nextC = cur.second+dc[i]
            if(nextR in 0 until m && nextC in 0 until n){
                val isFirst = (visited[nextR][nextC] == -1)
                when(board[nextR][nextC]){
                    0 -> {
                        if(isFirst || visited[nextR][nextC] > visited[cur.first][cur.second]){
                            queue.add(Pair(nextR, nextC))
                            visited[nextR][nextC] = visited[cur.first][cur.second]
                        }
                    }
                    1 -> {
                        if(isFirst || visited[nextR][nextC] > visited[cur.first][cur.second]+1){
                            queue.add(Pair(nextR, nextC))
                            visited[nextR][nextC] = visited[cur.first][cur.second]+1
                        }
                    }
                }
            }
        }
    }
    bw.write("${visited[m-1][n-1]}\n")
    bw.flush()
    bw.close()
    br.close()
}
