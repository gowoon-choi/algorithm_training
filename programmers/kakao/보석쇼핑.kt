
fun solution(gems: Array<String>): IntArray {
    var answer = IntArray(2)

    val gemCnt = HashMap<String, Int>()
    for (gem in gems){
        if(!gemCnt.containsKey(gem)){
            gemCnt.put(gem, 0)
        }
    }
    var type = 0
    var min = gems.size+1
    var front = 0
    var end = -1
    while (end < gems.size){
        if(type < gemCnt.size){
            end++
            if(end == gems.size) continue
            val cnt = gemCnt[gems[end]]!!
            if(cnt == 0){
                type++
            }
            gemCnt.replace(gems[end], cnt+1)
        }
        if(type == gemCnt.size){
            if(min > (end-front)+1){
                min = (end-front)+1
                answer[0] = front+1
                answer[1] = end+1
            }
            val cnt = gemCnt[gems[front]]!!
            gemCnt.replace(gems[front], cnt-1)
            front++
            if(cnt == 1){
                type--
            }
        }
    }

    return answer
}
