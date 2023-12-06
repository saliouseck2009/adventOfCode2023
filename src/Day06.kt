package src

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day06.txt")

fun main() {
    part1()
    part2()
}

private fun part1() {
    val times = day3Data.first().split(":").last().trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }
    val distances = day3Data.last().split(":").last().trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }

    val winNumberLength = getNumberOfWinNumbers(times, distances)
    println(winNumberLength.reduce { p, element -> p * element })
}

private fun part2() {
    val times =
        day3Data.first().split(":").last().trim().split(" ").filter { it.isNotBlank() }.joinToString("").toLong()
    val distances =
        day3Data.last().split(":").last().trim().split(" ").filter { it.isNotBlank() }.joinToString("").toLong()

    val winNumberLength = getNumberOfWinNumbers(listOf(times), listOf(distances))
    println(winNumberLength.reduce { p, element -> p * element })
}

private fun getNumberOfWinNumbers(
    times: List<Long>,
    distances: List<Long>
): MutableList<Long> {
    val winNumberLength = mutableListOf<Long>()
    for (i in times.indices) {
        var numberOfDifferentWay: Long = 0
        for (j in 0..times[i]) {
            if ((times[i] - j) * j > distances[i]) {
                numberOfDifferentWay++
            }
        }
        winNumberLength.add(numberOfDifferentWay)
    }
    return winNumberLength
}