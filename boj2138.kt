import java.io.*

fun isPossible(charArray: CharArray, target: String, length: Int, init: Int): Int{
    var count = init
    if(count == 1){
        charArray[0] = if(charArray[0]=='0') '1' else '0'
        charArray[1] = if(charArray[1]=='0') '1' else '0'
    }
    for (i in 1 until length){
        if(target[i-1] != charArray[i-1]){
            charArray[i-1] = if(charArray[i-1]=='0') '1' else '0'
            charArray[i] = if(charArray[i]=='0') '1' else '0'
            if(i<length-1) charArray[i+1] = if(charArray[i+1]=='0') '1' else '0'
            count++
        }
    }
    return if(String(charArray) == target) count else -1
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val length = br.readLine().toInt()
    val input = br.readLine()
    val target = br.readLine()

    // init - 0 : 0번째 누르지 않는 경우 / 1: 0번째 누르는 경우
    var result = isPossible(input.toCharArray(), target, length, 0)
    if(result == -1){
        result = isPossible(input.toCharArray(), target, length, 1)
    }
    bw.write("$result\n")
    bw.flush()
    bw.close()
    br.close()
}
