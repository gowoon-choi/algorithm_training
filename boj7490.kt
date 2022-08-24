import java.io.*

val result = mutableListOf<String>()

fun isZero(signs: Array<Char>): Boolean{
    val nums = mutableListOf<Int>()
    val signsNotEmpty = mutableListOf<Char>()
    var pre = 1
    signs.forEachIndexed { index, sign ->
        var next = index+2
        if(sign == ' '){
            next += pre * 10
        }else{
            nums.add(pre)
            signsNotEmpty.add(sign)
        }
        pre = next
    }
    nums.add(pre)

    var result = nums[0]
    signsNotEmpty.forEachIndexed { index, sign ->
        when(sign){
            '+' -> result += nums[index+1]
            '-' -> result -= nums[index+1]
        }
    }

    return result == 0
}

fun convertToString(signs: Array<Char>): String{
    var result = "1"
    signs.forEachIndexed { index, sign ->
        var num = (index+2).toString()
        result += sign
        result += num
    }
    return result
}

fun dfs(depth: Int, n: Int, signs: Array<Char>){
    if(depth == n){
        if(isZero(signs)){
            result.add(convertToString(signs))
        }
        return
    }

    // 공백
    signs[depth] = ' '
    dfs(depth+1, n, signs)

    // +
    signs[depth] = '+'
    dfs(depth+1, n, signs)

    // -
    signs[depth] = '-'
    dfs(depth+1, n, signs)
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val t = br.readLine().toInt()
    for(test in 0 until t){
        val n = br.readLine().toInt()
        result.clear()
        dfs(0, n-1, Array(n-1){ '_' })
        for (str in result){
            bw.write("$str\n")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
    br.close()
}
