
import java.util.*
import java.io.*
import kotlin.math.min

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st: StringTokenizer

    st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()-1
    val m = st.nextToken().toInt()-1
    val r = st.nextToken().toInt()

    var original = Array<Array<Int>>(n+1){
        st = StringTokenizer(br.readLine())
        Array(m+1) {
            st.nextToken().toInt()
    } }


    val dr = arrayOf(0, 1, 0, -1)
    val dc = arrayOf(-1, 0, 1, 0)

    var result = Array<Array<Int>>(n+1){ Array(m+1){ 0 } }
    val count : Int = min(n, m)/2
    var startR : Int
    var endR : Int
    var startC : Int
    var endC : Int
    var direction : Int
    var nextR : Int
    var nextC : Int
    for(cnt in 0..count){
        startR = 0+cnt
        endR = n-cnt
        startC = 0+cnt
        endC = m-cnt

        for(row in startR..endR){
            for(col in startC..endC){
                if(original[row][col] == 14 || original[row][col] == 19){
                    print("")
                }

                // 서
                if(row == startR && col != startC){
                    direction = 0
                }
                // 남
                else if(col == startC && row != endR){
                    direction = 1
                }
                // 동
                else if(row == endR && col != endC){
                    direction = 2
                }
                // 북
                else if(col == endC && row != startC) {
                    direction = 3
                }
                else {
                    continue
                }

                nextR = row
                nextC = col
                // 이동
                for(i in 0 until r%(((endR-startR)+(endC-startC))*2)){
                    nextR += dr[direction]
                    nextC += dc[direction]
                    if(nextR < startR || nextR > endR || nextC < startC || nextC > endC){
                        nextR -= dr[direction]
                        nextC -= dc[direction]
                        direction = (direction+1)%4
                        nextR += dr[direction]
                        nextC += dc[direction]
                    }
                }
                result[nextR][nextC] = original[row][col]
            }
        }
    }

    result.forEach { arr ->
        arr.forEach { elem ->
            bw.write("$elem ")
        }
        bw.newLine()
    }

    bw.close()
    br.close()
}
