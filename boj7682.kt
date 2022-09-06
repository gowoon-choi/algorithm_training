import java.io.*

const val SIZE = 3

enum class Type{
    RIGHT_CROSS, LEFT_CROSS, VERTICAL, HORIZONTAL
}

var OCount = 0
var XCount = 0
var OLines = mutableListOf<Type>()
var XLines = mutableListOf<Type>()

fun init(){
    OCount = 0
    XCount = 0
    OLines.clear()
    XLines.clear()
}

fun search(board: Array<Array<Char>>, depth: Int, way: Type, cur: Pair<Int, Int>, mark: Char){
    if(depth == SIZE){
        if(mark == 'O') OLines.add(way)
        else XLines.add(way)
        return
    }

    // mark 확인 > 백트레킹
    if(mark != board[cur.first][cur.second]) return

    when(way){
        Type.RIGHT_CROSS -> {
            search(board, depth+1, way, Pair(cur.first+1, cur.second+1), mark)
        }
        Type.LEFT_CROSS -> {
            search(board, depth+1, way, Pair(cur.first-1, cur.second+1), mark)
        }
        Type.VERTICAL -> {
            search(board, depth+1, way, Pair(cur.first+1, cur.second), mark)
        }
        Type.HORIZONTAL -> {
            search(board, depth+1, way, Pair(cur.first, cur.second+1), mark)
        }
    }

}

fun isPossible(): Boolean {
    val OLineCount = OLines.size
    val XLineCount = XLines.size
    // 1
    if(OCount != XCount && OCount+1 != XCount) return false
    // 2
    if(OLineCount == 0 && XLineCount == 0){
        if(OCount+XCount != 9) return false
    }
    else{
        // 3
        if(OLineCount > 0 && XLineCount > 0) return false
        // 6
        if(OLineCount + XLineCount > 3) return false
        if (OLineCount > 0){
            // 5
            if(OLineCount == 2){
                if(OLines[0] == Type.VERTICAL && OLines[1] == Type.VERTICAL) return false
                else if(OLines[0] == Type.HORIZONTAL && OLines[1] == Type.HORIZONTAL) return false
            }
            // 4
            if(OCount != XCount) return false
        }else{
            // 5
            if(XLineCount == 2){
                if(XLines[0] == Type.VERTICAL && XLines[1] == Type.VERTICAL) return false
                else if(XLines[0] == Type.HORIZONTAL && XLines[1] == Type.HORIZONTAL) return false
            }
            // 4
            if(OCount+1 != XCount) return false
        }
    }
    return true
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while (true){
        val str = br.readLine()
        if(str == "end") break
        init()
        val board = Array(SIZE){ row -> Array(SIZE){ col ->
            var ch = str[SIZE*row+col]
            if(ch == 'O') OCount++
            else if(ch == 'X') XCount++
            ch
        } }
        for(i in 0..2){
            if(i==0){
                if(board[0][i] != '.') search(board, 0, Type.RIGHT_CROSS, Pair(0, i), board[0][i])
            }
            else if(i==2){
                if(board[i][0] != '.') search(board, 0, Type.LEFT_CROSS, Pair(i, 0), board[i][0])
            }
            if(board[0][i] != '.') search(board, 0, Type.VERTICAL, Pair(0, i), board[0][i])
            if(board[i][0] != '.') search(board, 0, Type.HORIZONTAL, Pair(i, 0), board[i][0])
        }
        if(isPossible()) bw.write("valid")
        else bw.write("invalid")
        bw.write("\n")
        bw.flush()
    }

    bw.close()
    br.close()
}
