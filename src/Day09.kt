package src

import java.lang.Integer.sum

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day09.txt")
fun main() {
    part1()
    part2()
}

private fun part2() {
    val data = day3Data.map { it.split(" ").map { it.toInt() } }
    val tables = mutableListOf<List<List<Int>>>()
    val thirdHistories = mutableListOf<Int>()
    data.forEach { line ->
        val currentList = mutableListOf<MutableList<Int>>()
        currentList.add(line.toMutableList())
        while (true) {
            val maListe = mutableListOf<Int>()
            for (i in 1..<currentList.last().size) {
                maListe.add(currentList.last()[i] - currentList.last()[i - 1])
            }
            currentList.add(maListe)
            val valueNoteNull = maListe.firstOrNull { it != 0 }
            if (valueNoteNull == null) {
                currentList.last().add(0, 0)
                for (i in currentList.size - 2 downTo 0) {
                    currentList[i].add(0, currentList[i].first() - currentList[i + 1].first())
                }
                thirdHistories.add(currentList.first().first())
                break
            }
        }
        tables.add(currentList)
    }
    println(thirdHistories.sum())
}

private fun part1() {
    val data = day3Data.map { it.split(" ").map { it.toInt() } }
    val tables = mutableListOf<List<List<Int>>>()
    val thirdHistories = mutableListOf<Int>()
    data.forEach { line ->
        val currentList = mutableListOf<MutableList<Int>>()
        currentList.add(line.toMutableList())
        while (true) {
            val maListe = mutableListOf<Int>()
            for (i in 1..<currentList.last().size) {
                maListe.add(currentList.last()[i] - currentList.last()[i - 1])
            }
            currentList.add(maListe)
            val valueNoteNull = maListe.firstOrNull { it != 0 }
            if (valueNoteNull == null) {
                currentList.last().add(0)
                for (i in currentList.size - 2 downTo 0) {
                    currentList[i].add(currentList[i + 1].last() + currentList[i].last())
                }
                thirdHistories.add(currentList.first().last())
                break
            }
        }
        tables.add(currentList)
    }
    println(thirdHistories.sum())
}