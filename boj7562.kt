import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val dr = arrayOf(-1, -2, -2, -1, 1, 2, 2, 1)
    val dc = arrayOf(-2, -1, 1, 2, 2, 1, -1, -2)

    val t = br.readLine().toInt()
    for (i in 0 until t){
        val l = br.readLine().toInt()
        val (fr, fc) = br.readLine().split(" ").map{ it.toInt() }
        val (tr, tc) = br.readLine().split(" ").map{ it.toInt() }

        val visited = Array(l){ Array(l){ -1 } }
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(Pair(fr, fc))
        visited[fr][fc] = 0
        while (queue.isNotEmpty()){
            val curR = queue.peek().first
            val curC = queue.poll().second
            if(curR == tr && curC == tc){
                bw.write("${visited[tr][tc]}\n")
                bw.flush()
                break
            }
            var nextR: Int
            var nextC: Int
            for (w in 0..dr.lastIndex){
                nextR = curR+dr[w]
                nextC = curC+dc[w]
                if(nextR in 0 until l && nextC in 0 until l && visited[nextR][nextC]==-1){
                    queue.add(Pair(nextR, nextC))
                    visited[nextR][nextC] = visited[curR][curC]+1
                }
            }
        }
    }

    bw.close()
    br.close()
}
