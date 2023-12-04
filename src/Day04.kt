package src

import kotlin.math.pow

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day04.txt")
fun main() {
    part1()
    part2()
}

private fun part2() {
    var part1Result = 0.0
    val cards = mutableMapOf<Int, Int>()
    for (i in day3Data.indices) {
        cards[i] = 0
    }
    day3Data.forEachIndexed { index, line ->
        val (_, winningNumber, myNumber) = line.split(":", "|")
        val winningNumberList = convertToListNumber(winningNumber)
        val myNumberList = convertToListNumber(myNumber)
        val intersectList = winningNumberList.intersect(myNumberList.toSet())
        if (intersectList.isNotEmpty()) {
            cards[index] = cards[index]!! + 1
            for (i in index + 1..index + intersectList.size) {
                if (i <= day3Data.size) {
                    cards[i] = cards[i]!! + cards[index]!!
                }
            }
            part1Result += 2.0.pow(intersectList.size - 1)
        } else {
            cards[index] = cards[index]!! + 1
        }
    }
    println(cards.values.sum())
}

private fun part1() {
    var part1Result = 0.0
    day3Data.forEach { line ->
        val (_, winningNumber, myNumber) = line.split(":", "|")
        val winningNumberList = convertToListNumber(winningNumber)
        val myNumberList = convertToListNumber(myNumber)
        val intersectList = winningNumberList.intersect(myNumberList.toSet())
        if (intersectList.isNotEmpty()) {
            part1Result += 2.0.pow(intersectList.size - 1)
        }
    }
    println(part1Result.toInt())
}

fun convertToListNumber(line: String): List<String> = line.trim().split(" ").filter { it.isNotBlank() }

