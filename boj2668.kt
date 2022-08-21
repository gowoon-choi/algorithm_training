import java.io.*

fun dfs(start: Int, cur: Int, visited: Array<Boolean>, arr: Array<Int>, fin: Array<Boolean>){
    visited[cur] = true
    if(visited[arr[cur]]){
        if(arr[cur] == start){
            fin[start] = true
            return
        }else{
            fin[start] = false
            visited[cur] = false
            return
        }
    }
    dfs(start, arr[cur], visited, arr, fin)
    if(!fin[start]) visited[cur] = false
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val arr = Array(n+1){
        if(it == 0) 0
        else br.readLine().toInt()
    }

    val visited = Array(n+1){ false}
    val fin = Array(n+1){ false }
    for(i in 1..n){
        if(!visited[i]) dfs(i, i, visited, arr, fin)
    }

    var nodes = mutableListOf<Int>()
    for (index in visited.indices){
        if(visited[index]) nodes.add(index)
    }

    bw.write("${nodes.size}\n")
    for (result in nodes){
        bw.write("$result\n")
    }

    bw.flush()
    bw.close()
    br.close()
}
