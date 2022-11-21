import java.util.regex.Pattern

val set = mutableSetOf<String>()

fun search(depth: Int, visited: Array<Char>, target: Array<ArrayList<Int>>){
    if(depth == target.size){
        set.add(visited.joinToString())
        return
    }
    for(n in target[depth]){
        if(visited[n] == 'F'){
            visited[n] = 'T'
            search(depth+1, visited, target)
            visited[n] = 'F'
        }
    }
}

fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
    var answer = 0

    val targetArr = Array(banned_id.size){ arrayListOf<Int>() }
    for(ban in banned_id.indices){
        val reg = banned_id[ban].replace("*", "[a-z0-9]{1}")
        val pattern = Pattern.compile(reg)
        for(user in user_id.indices){
            val matcher = pattern.matcher(user_id[user])
            if(matcher.matches()){
                targetArr[ban].add(user)
            }
        }
    }

    search(0, Array(user_id.size){ 'F' }, targetArr)

    answer = set.size
    return answer
}
