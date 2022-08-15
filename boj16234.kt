import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()
    val r = st.nextToken().toInt()

    val map = Array(n){
        val st = StringTokenizer(br.readLine())
        Array(n){ st.nextToken().toInt() }
    }

    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(1, 0, -1, 0)

    var flag = true
    var day = 0
    while (flag){
        flag = false
        val visited = Array(n){ Array(n){ false } }
        for(row in map.indices){
            for(col in map[row].indices){
                // bfs
                val queue = LinkedList<Pair<Int, Int>>()
                var sum = 0
                val areas = mutableListOf<Pair<Int, Int>>()
                if(visited[col][row]){
                   continue
                }

                queue.add(Pair(col, row))
                visited[col][row] = true
                areas.add(Pair(col, row))
                sum += map[col][row]
                while (!queue.isEmpty()){
                    val cur = queue.poll()
                    // 탐색
                    for(i in 0 until 4){
                        val next = Pair(cur.first+dx[i], cur.second+dy[i])
                        if(next.first in 0 until n && next.second in 0 until n && !visited[next.first][next.second]){
                            val diff = Math.abs(map[cur.first][cur.second]-map[next.first][next.second])
                            if(diff in l..r){
                                queue.add(next)
                                visited[next.first][next.second] = true
                                areas.add(Pair(next.first, next.second))
                                sum += map[next.first][next.second]
                            }
                        }
                    }
                }

                if(areas.size > 1){
                    flag = true
                    // 재배치
                    val amount = sum/areas.size
                    for (point in areas){
                        map[point.first][point.second] = amount
                    }
                }
            }
        }
        if(flag) day++
    }
    bw.write("$day\n")

    bw.close()
    br.close()
}
