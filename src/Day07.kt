package src

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day07.txt")

data class Hand(val value: String, val score: Int, val category: Int)

val handStrength = mutableMapOf<Char, Int>(
    '2' to 2, '3' to 3, '4' to 4, '5' to 5, '6' to 6, '7' to 7,
    '8' to 8, '9' to 9, 'T' to 10, 'J' to 11, 'Q' to 12, 'K' to 13, 'A' to 14,
)
val cardComparator = Comparator<Hand> { c1, c2 -> compareCards(c1.value, c2.value) }
fun compareCards(c1: String, c2: String): Int {
    println(c1 + " " + c2)
    for (i in c1.indices) {
        if (handStrength[c1[i]]!! > handStrength[c2[i]]!!) return 1
        else if (handStrength[c1[i]]!! < handStrength[c2[i]]!!) return -1
    }
    return 0
}

fun main() {
   
    val hands = mutableListOf<Hand>()
    day3Data.forEach {
        val line = it.split(" ")
        hands.add(Hand(value = line.first(), score = line.last().trim().toInt(), getDistinctCharacter(line.first())))
        println(line)
    }

    // val handsCategory = hands.groupBy { it.category }.toSortedMap()
    val sortedHands = hands.sortedWith(compareByDescending<Hand> { it.category }.then(cardComparator.reversed()))
    var result = 0
    sortedHands.forEachIndexed { index, hand ->
        result += hand.score * (sortedHands.size - index)
    }
    println(result)
}


fun getDistinctCharacter(str: String): Int {
    val occurenceMap = mutableMapOf<String, Int>()
    for (c in str) {
        occurenceMap.putIfAbsent(c.toString(), 0)
        occurenceMap[c.toString()] = occurenceMap[c.toString()]!! + 1
    }
    if (occurenceMap.containsValue(5)) {
        return 7
    } else if (occurenceMap.containsValue(4)) {
        return 6
    } else if (occurenceMap.containsValue(3)) {
        if (occurenceMap.containsValue(2)) {
            return 5
        } else {
            return 4
        }
    } else if (occurenceMap.size == 3) {
        return 3
    } else if (occurenceMap.size == 4) {
        return 2
    } else {
        return 1
    }
}