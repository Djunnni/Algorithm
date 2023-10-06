import java.io.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N: Int = Integer.parseInt(br.readLine())

    val array: MutableList<Int> = mutableListOf<Int>()
    for(i in 0..N-1) {
        val level = Integer.parseInt(br.readLine())
        array.add(level)
    }

    var answer = 0;
    for(i in N-1 downTo 1) {
        if(array.get(i) <= array.get(i - 1)) {
            val gap = array.get(i - 1) - array.get(i)
            array.set(i - 1, array.get(i - 1) - (gap + 1))
            answer += (gap + 1)
        }
    }

    print(answer)
}