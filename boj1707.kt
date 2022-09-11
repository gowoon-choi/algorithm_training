import java.io.*
import java.util.*

fun search(start: Int, graph: Array<MutableList<Int>>, visited: Array<Int>): Boolean{
    val queue = LinkedList<Int>()
    queue.add(start)
    visited[start] = 0
    while (queue.isNotEmpty()){
        val cur = queue.poll()
        val color = visited[cur]
        for(elem in graph[cur]){
            when(visited[elem]){
                -1 -> {
                    queue.add(elem)
                    visited[elem] = (color+1)%2
                }
                color -> {
                    return false
                }
                else -> {
                    continue
                }
            }
        }
    }
    return true
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = br.readLine().toInt()
    for(test in 0 until t) {
        val (v, e) = br.readLine().split(" ")
        val graph = Array(v.toInt() + 1) { mutableListOf<Int>() }
        for (edge in 0 until e.toInt()) {
            val (e1, e2) = br.readLine().split(" ")
            graph[e1.toInt()].add(e2.toInt())
            graph[e2.toInt()].add(e1.toInt())
        }

        // 탐색
        var flag = true
        val visited = Array(v.toInt() + 1) { -1 } // -1 : 미방문 / 0 : 그룹1 / 1 : 그룹2
        for(i in 1..v.toInt()){
            if(visited[i] == -1){
                if(!search(i, graph, visited)){
                    flag = false
                    bw.write("NO\n")
                    bw.flush()
                    break
                }
            }
        }
        if(flag){
            bw.write("YES\n")
            bw.flush()
        }
    }
    bw.close()
    br.close()
}
