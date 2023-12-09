package src

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day08.txt")
private const val RESULT = "ZZZ"
private const val LEFT = 'L'
private const val RIGHT = 'R'
private const val START = "AAA"
private const val CHARACTER_START = 'A'
private const val CHARACTER_END = 'Z'
fun main() {
    //part1()
    part2()
}

private fun part2() {
    val pattern = day3Data.first()
    val gamesData = day3Data.drop(2)
    val nodes = extractAllNode(gamesData)
    var isFound = true
    var currentKeys = nodes.keys.filter { it.last() == CHARACTER_START }
    println(currentKeys)
    var counter = 0L
    while (isFound) {
        pattern.forEach { currChar ->
            counter++
            currentKeys = currentKeys.map {
                nodes[it]?.get(currChar)!!
            }
            if (checkIfAllLastCharacterIsZ(currentKeys)) {
                isFound = false
            }
        }
        println(counter)
    }
    println(counter)
}

private fun checkIfAllLastCharacterIsZ(keys: List<String>): Boolean {
    keys.forEach {
        if (it.last() != CHARACTER_END) {
            return false
        }
    }
    return true
}

private fun part1() {
    val pattern = day3Data.first()
    val gamesData = day3Data.drop(2)
    val nodes = extractAllNode(gamesData)
    var isFound = true
    var currentKey = START
    var counter = 0L
    while (isFound) {
        pattern.forEach { currChar ->
            counter++
            val currentNode = nodes[currentKey]?.get(currChar)
            if (currentNode == RESULT) {
                isFound = false
            } else {
                currentKey = currentNode!!
            }
        }
    }
    println(counter)
}

private fun extractAllNode(gamesData: List<String>): MutableMap<String, Map<Char, String>> {
    val nodes = mutableMapOf<String, Map<Char, String>>()
    gamesData.forEach {
        val line = it.split(" = ")
        val nodeValue = line.last().replace("(", "").replace(")", "")
            .split(", ")
        nodes[line.first()] = mapOf(LEFT to nodeValue.first(), RIGHT to nodeValue.last())
    }
    return nodes
}

