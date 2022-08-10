import java.io.*
import java.util.*

fun step1(durability: MutableList<Int>, robot: MutableList<Boolean>){
    durability.add(0, durability.removeLast())
    robot.removeLast()
    robot.add(0, false)
}

fun step2(durability: MutableList<Int>, robot: MutableList<Boolean>){
    for(index in robot.size downTo 1){
        if(robot[index-1] && durability.elementAt(index) > 0){ // 로봇이 있고, 다음칸 내구성이 0보다 클 떄
            if(index == robot.size){ // 마지막 칸으로 이동해서 내리기
                robot[index-1] = false
                durability[index]--
            }
            else if (!robot[index]){ // 마지막 칸으로 이동하는 것은 아니지만 다음 칸이 비어잇어서 이동 가능
                robot[index] = true
                robot[index-1] = false
                durability[index]--
            }
        }
    }
}

fun step3(durability: MutableList<Int>, robot: MutableList<Boolean>){
    if(durability[0] > 0){
        robot[0] = true
        durability[0]--
    }
}

fun step4(durability: MutableList<Int>, k: Int): Boolean{
    var count = 0
    for(elem in durability){
        if(elem == 0) count++
    }
    return count < k
}

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val durability: MutableList<Int> = MutableList(2*n){
        st.nextToken().toInt()
    }
    val robot: MutableList<Boolean> = MutableList(n-1) { false }

    var flag = true
    var count = 0
    while (flag){
        count++
        step1(durability, robot)
        step2(durability, robot)
        step3(durability, robot)
        flag = step4(durability, k)
    }

    bw.write("$count\n")
    bw.close()
    br.close()
}
