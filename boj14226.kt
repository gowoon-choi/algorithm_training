import java.io.*
import java.util.*

const val MAX = 1000

class Emoji(val length: Int, val clipboard: Int, val count: Int) : Comparable<Emoji> {
    override fun compareTo(other: Emoji): Int {
        return this.count-other.count
    }
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val s = br.readLine().toInt()

    val visited = Array(MAX+1){ Array(MAX+1){ 0 } }
    val pq = PriorityQueue<Emoji>()
    pq.add(Emoji(1, 1, 1)) // init and first copy
    visited[1][1] = 1
    while (pq.isNotEmpty()){
        val cur = pq.poll()
        if(cur.length == s){
            bw.write("${cur.count}\n")
            bw.flush()
            break
        }
        // paste
        var next = Emoji(cur.length+cur.clipboard, cur.clipboard, cur.count+1)
        if(next.length in 2..MAX && visited[next.length][next.clipboard]==0){
            pq.add(next)
            visited[next.length][next.clipboard] = next.count
        }
        // delete
        next = Emoji(cur.length-1, cur.clipboard, cur.count+1)
        if(next.length in 2..MAX && visited[next.length][next.clipboard]==0){
            pq.add(next)
            visited[next.length][next.clipboard] = next.count
        }
        // copy and paste
        next = Emoji(cur.length*2, cur.length, cur.count+2)
        if(next.length in 2..MAX && visited[next.length][next.clipboard]==0){
            pq.add(next)
            visited[next.length][next.clipboard] = next.count
        }
    }

    bw.close()
    br.close()
}
