
import java.util.*
import java.io.*

fun main(){
    val max = 200_000_1

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st: StringTokenizer

    st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    var visited = Array<Boolean>(max) { false }
    var queue : PriorityQueue<Pair<Int, Int>> = PriorityQueue(kotlin.Comparator { o1, o2 -> o1.second - o2.second })

    var cur = Pair(n, 0)
    queue.add(cur)

    var next : Int
    while (!queue.isEmpty()){
        cur = queue.poll()
        visited[cur.first] = true
        if(cur.first == k) break

        // *2
        next = cur.first*2
        if(next < max && !visited[next]){
            queue.add(Pair(next, cur.second))
        }

        // +1
        next = cur.first+1
        if(next < max && !visited[next]){
            queue.add(Pair(next, cur.second+1))
        }

        // -1
        next = cur.first-1
        if(next >= 0 && !visited[next]){
            queue.add(Pair(next, cur.second+1))
        }
    }

    bw.write("${cur.second}\n")
    bw.close()
    br.close()
}
