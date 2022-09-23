import kotlin.math.min

val MAX = 20_000_001

fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
    var answer: Int = 0

    val graph = Array(n){ col ->
        Array(n){ row ->
            if(col == row) 0
            else MAX
    } }
    for(fare in fares){
        graph[fare[0]-1][fare[1]-1] = fare[2]
        graph[fare[1]-1][fare[0]-1] = fare[2]
    }

    for(i in 0 until n){
        for(row in 0 until n){
            if(i==row) continue
            for(col in 0 until n){
                graph[row][col] = min(graph[row][col], graph[row][i]+graph[i][col])
            }
        }
    }

    answer = graph[s-1][a-1] + graph[s-1][b-1]
    for(i in graph.indices){
        answer = min(answer, graph[s-1][i] + graph[i][a-1] + graph[i][b-1])
    }

    return answer
}
