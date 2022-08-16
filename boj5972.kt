import java.io.*
import java.util.*
import kotlin.math.min

const val INF = 50000001
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    // n m 입력
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    var node1: Int
    var node2: Int
    var weight: Int
    // map 초기화
    val map : Array<MutableList<Pair<Int, Int>>> = Array(n+1){ mutableListOf() }
    for(i in 0 until m){
        st = StringTokenizer(br.readLine())
        node1 = st.nextToken().toInt()
        node2 = st.nextToken().toInt()
        weight = st.nextToken().toInt()
        map[node1].add(Pair(node2, weight))
        map[node2].add(Pair(node1, weight))
    }

    //  dis 초기화
    val dis = Array(n+1){ INF }
    dis[1] = 0

    // visited, pq 초기화
    val visited = Array(n+1){ false }
    val pq = PriorityQueue<Pair<Int,Int>>(kotlin.Comparator { o1, o2 ->  o1.second-o2.second})

    // 시작값 초기화
    pq.add(Pair(1, 0))
    // 다익스트라
    var cur: Pair<Int, Int>
    while (pq.isNotEmpty()){
        cur = pq.poll()
        if(visited[cur.first]) continue
        visited[cur.first] = true
        dis[cur.first] = cur.second
        for(p in map[cur.first]){
            if(!visited[p.first]){
                pq.add(Pair(p.first, dis[cur.first]+p.second))
            }
        }
    }

    bw.write("${dis[n]}\n")

    bw.flush()
    bw.close()
    br.close()
}
