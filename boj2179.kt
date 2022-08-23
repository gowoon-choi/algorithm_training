import java.io.*
import kotlin.math.min
import kotlin.math.max

fun getSimilarity(str1: String, str2:String): Int{
    val n = min(str1.length, str2.length)
    var count = 0
    for(i in 0 until n){
        if(str1[i] == str2[i]) count++
        else break
    }
    return count
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val map = HashMap<String, Int>()
    val arr = Array(n){
        val str = br.readLine()
        map[str] = it
        str
    }
    val sortedArr = arr.sorted()

    var maximum = 0
    val list = mutableListOf<Pair<Int, Int>>()
    for(i in sortedArr.indices){
        for(j in i+1..sortedArr.lastIndex){
            val similarity = getSimilarity(sortedArr[i], sortedArr[j])
            val min = min(map[sortedArr[i]]!!, map[sortedArr[j]]!!)
            val max = max(map[sortedArr[i]]!!, map[sortedArr[j]]!!)
            if(similarity == maximum){
                list.add(Pair(min, max))
            }else if(similarity > maximum){
                list.clear()
                list.add(Pair(min, max))
                maximum = similarity
            }else{
                break
            }
        }
    }

    list.sortWith(Comparator { o1, o2 ->
        if(o1.first == o2.first) o1.second-o2.second
        else o1.first-o2.first
    })
    bw.write("${arr[list[0].first]}\n${arr[list[0].second]}\n")

    bw.flush()
    bw.close()
    br.close()
}
