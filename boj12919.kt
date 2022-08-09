import java.io.*

var result = false
fun dfs(cur: String, visited: MutableSet<String>, target: String){
    if(cur == target){
        result = true
        return
    }

    visited.add(cur)

    if(cur[cur.lastIndex] == 'A'){
        val next = cur.removeSuffix("A")
        if(cur.length > target.length && !visited.contains(next)){
            dfs(next, visited, target)
            if(result) return
        }
    }

    if(cur[0] == 'B'){
        val next = cur.removePrefix("B").reversed()
        if(cur.length > target.length && !visited.contains(next)){
            dfs(next, visited, target)
            if(result) return
        }
    }
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var input = br.readLine()
    val target = br.readLine()

    dfs(target, mutableSetOf(), input)

    val result = if(result) "1" else "0"
    bw.write("$result\n")
    bw.close()
    br.close()
}
