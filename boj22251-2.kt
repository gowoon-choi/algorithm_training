import java.io.*

val nums = arrayOf(
    0b1110111,
    0b0010010,
    0b1011101,
    0b1011011,
    0b0111010,
    0b1101011,
    0b1101111,
    0b1010010,
    0b1111111,
    0b1111011
)

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
            count += (nums[target[j]-'0'] xor nums[cur[j]-'0']).countOneBits()
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
