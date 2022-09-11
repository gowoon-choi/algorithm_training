import java.io.*
import kotlin.math.max

// row, col 을 포함하는 가장 길게 연결된 행/열의 길이
fun count(board: Array<Array<Char>>, row: Int, col: Int, n: Int): Int{
    var max = 0
    var cur = board[row][col]
    // row, col을 포함하는 가로로 가장 긴 길이
    var count = 1
    // row, col 보다 우측에 같은 색 카운트
    var nextC = col+1
    while (nextC < n && board[row][nextC]==cur){
        count++
        nextC++
    }
    // row, col 보다 좌측에 같은 색 카운트
    nextC = col-1
    while (nextC >= 0 && board[row][nextC]==cur){
        count++
        nextC--
    }
    max = max(max, count)

    // row, col을 포함하는 세로로 가장 긴 길이
    count = 1
    // row, col 보다 위쪽에 같은 색 카운트
    var nextR = row-1
    while (nextR >= 0 && board[nextR][col]==cur){
        count++
        nextR--
    }
    // row, col 보다 아래쪽 같은 색 카운트
    nextR = row+1
    while (nextR < n && board[nextR][col]==cur){
        count++
        nextR++
    }
    max = max(max, count)

    return max
}

// 교환하지 않은 상태에서 가장 긴 길이
fun max(board: Array<Array<Char>>, n: Int): Int{
    var max = 0
    val visited = Array(n){ Array(n){ false } }
    for(row in board.indices){
        for(col in board[row].indices){
            if(visited[row][col]) continue
            visited[row][col] = true
            val cur = board[row][col]
            var count = 1
            // row, col 기준으로 가로 count
            var nextC = col+1
            while (nextC < n && board[row][nextC]==cur){
                count++
                nextC++
            }
            max = max(max, count)
            // row, col 기준으로 세로 count
            count = 1
            var nextR = row+1
            while (nextR < n && board[nextR][col]==cur){
                count++
                nextR++
            }
            max = max(max, count)
        }
    }
    return max
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val board = Array(n){
        val str = br.readLine()
        Array(n){ col ->
            str[col]
        }
    }

    var max = max(board, n) // n>=3 이므로 초기에 가장 긴 길이를 max로 설정
    if(max < n){ // max가 n과 같으면 이미 그것이 최대이므로 탐색 X
        for(row in board.indices){
            for(col in board[row].indices){
                val cur = board[row][col]
                // row, col 기준으로 우측 캔디, 아래측 캔디와만 교환하면 모든 교환 경우
                // 우측 캔디와 교환
                if(col+1 < n){ // 범위 체크
                    val target = board[row][col+1]
                    if(cur != target){ // 서로 다른 색이라서 교환 가능한지 체크
                        // swap
                        board[row][col] = target
                        board[row][col+1] = cur
                        // count - 교환한 두 칸을 포함하는 가로 세로 캔디 개수 카운팅
                        // 카운팅 결과와 max 비교하여 업데이트
                        max = max(max, count(board, row, col, n))
                        max = max(max, count(board, row, col+1, n))
                        // 초기 상태로 다시 swap
                        board[row][col] = cur
                        board[row][col+1] = target
                    }
                }
                // 아래측 캔디와 교환
                if(row+1 < n){
                    val target = board[row+1][col]
                    if(cur != target){
                        // swap
                        board[row][col] = target
                        board[row+1][col] = cur
                        // count - 교환한 두 칸을 포함하는 가로 세로 캔디 개수 카운팅
                        // 카운팅 결과와 max 비교하여 업데이트
                        max = max(max, count(board, row, col, n))
                        max = max(max, count(board, row+1, col, n))
                        // 초기 상태로 다시 swap
                        board[row][col] = cur
                        board[row+1][col] = target
                    }
                }
            }
        }
    }
    bw.write("$max\n")

    bw.flush()
    bw.close()
    br.close()
}
