import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

val MAX_VAL = 200001
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val (v, e) = readLine().split(" ").map { it.toInt() }
    val start = readLine().toInt()-1

    val result = Array(v){ MAX_VAL }
    result[start] = 0

    val pq = PriorityQueue<Pair<Int, Int>>(kotlin.Comparator { o1, o2 ->  o1.second-o2.second })

    val graph = ArrayList<ArrayList<Pair<Int, Int>>>()
    for(i in 0 until v){
        graph.add(arrayListOf())
    }

    for (i in 0 until e){
        val (f, t, w) = readLine().split(" ").map { it.toInt()-1 }
        if(f==start){
            result[t] = max(result[t], w+1)
            pq.add(Pair(t, w+1))
        }else{
            graph[f].add(Pair(t, w+1))
        }
    }

    while(pq.isNotEmpty()){
        val cur = pq.poll()
        if(cur.second > result[cur.first]) continue
        for(i in graph[cur.first]){
            if(result[i.first] > i.second+cur.second){
                result[i.first] = i.second+cur.second
                pq.add(Pair(i.first, result[i.first]))
            }
        }
    }

    for (i in result){
        if(i==MAX_VAL) println("INF")
        else println(i)
    }
}
