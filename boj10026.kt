import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val dc = arrayOf(0, 0, 1, -1)
val dr = arrayOf(1, -1, 0, 0)

fun rgWeakComp(ch1: Char, ch2: Char): Boolean{
    return when(ch1){
        in arrayOf('R', 'G') -> ch2 in arrayOf('R', 'G')
        else -> ch1==ch2
    }
}
fun search(grid: Array<String>, n: Int, isRGWeak: Boolean): Int{
    var count = 0
    val visited = Array(n){ Array(n){ false } }
    val queue = LinkedList<Pair<Int, Int>>()
    for(row in 0 until n){
        for(col in 0 until n){
            if(!visited[row][col]){
                queue.add(Pair(row, col))
                visited[row][col] = true
                count++
            }
            while (queue.isNotEmpty()){
                val cur = queue.poll()
                for(d in 0 until 4){
                    val nR = cur.first+dr[d]
                    val nC = cur.second+dc[d]
                    if(nR in 0 until n && nC in 0 until n && !visited[nR][nC]){
                        if((isRGWeak&&rgWeakComp(grid[cur.first][cur.second], grid[nR][nC])) || (!isRGWeak&&grid[cur.first][cur.second] == grid[nR][nC])){
                            queue.add(Pair(nR, nC))
                            visited[nR][nC] = true

                        }
                    }
                }
            }
        }
    }
    return count
}

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val grid = Array(n){
        readLine()
    }
    println("${search(grid, n, false)} ${search(grid, n, true)}")
}
