import java.io.*

val nums = arrayOf(
    arrayOf(1, 1, 1, 0, 1, 1, 1), // 0
    arrayOf(0, 0, 1, 0, 0, 1, 0), // 1
    arrayOf(1, 0, 1, 1, 1, 0, 1), // 2
    arrayOf(1, 0, 1, 1, 0, 1, 1), // 3
    arrayOf(0, 1, 1, 1, 0, 1, 0), // 4
    arrayOf(1, 1, 0, 1, 0, 1, 1), // 5
    arrayOf(1, 1, 0, 1, 1, 1, 1), // 6
    arrayOf(1, 0, 1, 0, 0, 1, 0), // 7
    arrayOf(1, 1, 1, 1, 1, 1, 1), // 8
    arrayOf(1, 1, 1, 1, 0, 1, 1) // 9
)

fun diff(num1: Int, num2: Int): Int{ // num1과 num2 반전 개수 구하는 함수
    return if(num1 == num2) 0
    else{
        var count = 0
        for(i in 0 until 7){
            if(nums[num1][i]+nums[num2][i]==1) count++ // 7개의 각 표시등 켜진 여부가 다르면 카운트
        }
        count
    }
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n,k,p,x) = br.readLine().split(' ').map{it.toInt()}

    var flag: Boolean
    var result = 0 // 결과값
    val target = x.toString().padStart(k, '0') // 현재 층 자리수에 맞게 앞에 0으로 채운 문자열
    for(i in 1..n){
        val cur = i.toString().padStart(k, '0') // 1부터 n층까지 루프 돌면서 자리수에 맞게 앞에 0으로 채운 문자열
        var count = 0 // 현재 층과 i층의 반전 개수 카운트 변수
        flag = true // p개 이내로 반전해서 가능한지 여부
        for(j in 0 until k){

            count += diff(target[j]-'0', cur[j]-'0') // 반전 개수 더해주기
            if(count > p){ // p개보다 많이 반전해야 하면 flag 바꿔주고 break
                flag = false
                break
            }
        }
        // p개 이내로 반전해서 가능한 층이면 result 증가
        if(flag) result++
    }

    bw.write("${result-1}\n") // 현재층은 빼준다
    bw.flush()
    bw.close()
    br.close()
}
