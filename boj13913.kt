import java.io.*
import java.util.*

const val MAX = 100000

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    if (n > k){
        bw.write("${n-k}\n")
        for(i in n downTo k) bw.write("$i ")
    } else if (n == k){
        bw.write("0\n")
        bw.write("$n\n")
    } else {
        val visited = Array(200001){ -1 }
        val queue = LinkedList<Pair<Int,Int>>()
        queue.add(Pair(n, 0))
        visited[n] = -2
        while (queue.isNotEmpty()){
            val cur = queue.poll()
            if(cur.first == k){
                bw.write("${cur.second}\n")
                val path = StringBuilder()
                var from = k
                while (from != -2){
                    path.insert(0, "$from ")
                    from = visited[from]
                }
                bw.write("${path}\n")
                break
            }
            // -1
            var nextPosition: Int = cur.first-1
            if(nextPosition >= 0 && visited[nextPosition]==-1){
                queue.add(Pair(nextPosition, cur.second+1))
                visited[nextPosition] = cur.first
            }
            // +1
            nextPosition = cur.first+1
            if(nextPosition <= MAX && visited[nextPosition]==-1){
                queue.add(Pair(nextPosition, cur.second+1))
                visited[nextPosition] = cur.first
            }
            // *2
            nextPosition = cur.first*2
            if(nextPosition <= MAX && visited[nextPosition]==-1){
                queue.add(Pair(nextPosition, cur.second+1))
                visited[nextPosition] = cur.first
            }
        }
    }
    bw.close()
    br.close()
}
