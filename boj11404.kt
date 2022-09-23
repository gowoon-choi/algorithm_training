import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

const val MAX_VAL = 10000001
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val board = Array(n+1){ row ->
        Array(n+1){ col->
            if(row == col) 0
            else MAX_VAL
    } }
    val m = readLine().toInt()
    for(i in 0 until m){
        val (f, t, w) = readLine().split(" ").map { it.toInt() }
        if(board[f][t] > w) board[f][t] = w
    }

    for(i in 1..n){
        for(row in 1..n){
            if(i == row) continue
            if(board[row][i] == MAX_VAL) continue
            for(col in 1..n){
                board[row][col] = min(board[row][col], board[row][i]+board[i][col])
            }
        }
    }

    for(row in 1..n){
        for(col in 1..n){
            if(board[row][col] == MAX_VAL) print("0 ")
            else print("${board[row][col]} ")
        }
        println()
    }
}
