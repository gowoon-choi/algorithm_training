import java.io.*
import java.util.*
import kotlin.collections.HashSet

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()

    var count = 0
    val stack = Stack<Int>()
    for(i in 0 until n){
        var (x, y)  = br.readLine().split(" ").map { it.toInt() }
        if(stack.isNotEmpty() && stack.peek() > y) {
            val set = HashSet<Int>()
            while (stack.isNotEmpty() && stack.peek() > y){
                set.add(stack.pop())
            }
            count+=set.size
        }
        if(y > 0) stack.push(y)
    }
    if(stack.isNotEmpty()){
        val set = HashSet<Int>()
        while (stack.isNotEmpty()){
            set.add(stack.pop())
        }
        count+=set.size
    }
    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}
