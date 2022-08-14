import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = br.readLine().toInt()
    for (i in 0 until t){
        val string = br.readLine()
        val count = br.readLine().toInt()
        val arr = Array<MutableList<Int>>(26){mutableListOf<Int>()}

        string.forEachIndexed { index, ch ->
            arr[ch-'a'].add(index)
        }

        val list = mutableListOf<Int>()
        for(counted in arr){
            if(counted.size >= count){
                for(index in 0..(counted.size-count)){
                    list.add(counted[index+(count-1)]-counted[index]+1)
                }
            }
        }

        if(list.isEmpty()) bw.write("-1\n")
        else{
            list.sort()
            bw.write("${list[0]} ${list[list.lastIndex]}\n")
        }
    }

    bw.close()
    br.close()
}
