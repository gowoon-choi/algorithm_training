import java.io.BufferedReader
import java.io.InputStreamReader


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val t = readLine().toInt()
    for(test in 0 until t){
        val command = readLine()
        val size = readLine().toInt()
        var arrStr = readLine()
        arrStr = arrStr.substring(1, arrStr.length-1)
        val arr = arrStr.split(",")
        var front = 0
        var end = size-1
        var frontPtr = front
        var errorFlag = false
        for (com in command){
            when(com){
                'R' -> {
                    frontPtr = if(frontPtr == front) end else front
                }
                'D' -> {
                    if(front > end){
                        errorFlag = true
                        println("error")
                        break
                    }
                    frontPtr = if(frontPtr == front){
                        ++front
                    } else{
                        --end
                    }
                }
            }
        }
        if(!errorFlag){
            // TODO
            val result = Array(end-front+1){
                if(frontPtr==front) arr[front+it]
                else arr[end-it]
            }
            println(result.contentDeepToString().replace(" ", ""))
        }
    }
}
