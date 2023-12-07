package src

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day07.txt")
private const val JOKER = 'J'
fun main() {
    part1()
    part2()
}

private fun part2() {
    val hands = mutableListOf<Hand>()
    day3Data.forEach {
        val line = it.split(" ")
        hands.add(
            Hand(
                line.first(),
                line.last().trim().toInt(),
                getDistinctCharacterForPart2(line.first(), isPart2 = true)
            )
        )
    }
    val sortedHands = hands.sortedWith(compareByDescending<Hand> { it.category }.then(cardComparatorPartTwo.reversed()))
    var result = 0
    sortedHands.forEachIndexed { index, hand ->
        result += hand.score * (sortedHands.size - index)
    }
    println(result)
}

private fun part1() {
    val hands = mutableListOf<Hand>()
    day3Data.forEach {
        val line = it.split(" ")
        hands.add(
            Hand(value = line.first(), score = line.last().trim().toInt(), getDistinctCharacterForPart2(line.first()))
        )
    }
    val sortedHands = hands.sortedWith(compareByDescending<Hand> { it.category }.then(cardComparator.reversed()))
    var result = 0
    sortedHands.forEachIndexed { index, hand -> result += hand.score * (sortedHands.size - index) }
    println(result)
}

data class Hand(val value: String, val score: Int, val category: Int)

val handStrengthPart2 = mutableMapOf(
    'J' to 1, '2' to 2, '3' to 3, '4' to 4, '5' to 5, '6' to 6, '7' to 7,
    '8' to 8, '9' to 9, 'T' to 10, 'Q' to 12, 'K' to 13, 'A' to 14,
)

/**
 * This function is used to transform the joker to the strongest item
 * @param value the value to transform
 * @return the transformed value
 */
fun transformJokerToStrongestItem(value: String): String {
    if (value.contains(JOKER) && value.count { it == JOKER } != 5) {
        val list = value.toList()
        val strongestKey = list.filter { it != JOKER }.groupingBy { it }.eachCount().maxBy { it.value }
        return value.replace(JOKER, strongestKey.key)
    } else {
        return value
    }
}

/**
 * This function is used to get the weight of the hand
 * @param str the value to transform
 * @param isPart2 if the function is used for part 2
 * @return the weight for the hand
 */
fun getDistinctCharacterForPart2(str: String, isPart2: Boolean = false): Int {
    var currentValue = str
    if (isPart2) {
        currentValue = transformJokerToStrongestItem(str)
    }
    val occurenceMap = currentValue.toList().groupingBy { it }.eachCount()
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

/** This function is used to compare the cards for part 2 it allows us to implement our own comparator
 * @param c1 the first card
 * @param c2 the second card
 * @return 1 if the first card is stronger, -1 if the second card is stronger, 0 if they are equal
 */
val cardComparatorPartTwo = Comparator<Hand> { c1, c2 -> compareCardsPartTwo(c1.value, c2.value) }
fun compareCardsPartTwo(c1: String, c2: String): Int {
    for (i in c1.indices) {
        if (handStrengthPart2[c1[i]]!! > handStrengthPart2[c2[i]]!!) return 1
        else if (handStrengthPart2[c1[i]]!! < handStrengthPart2[c2[i]]!!) return -1
    }
    return 0
}

val handStrength = mutableMapOf(
    '2' to 2, '3' to 3, '4' to 4, '5' to 5, '6' to 6, '7' to 7,
    '8' to 8, '9' to 9, 'T' to 10, 'J' to 11, 'Q' to 12, 'K' to 13, 'A' to 14,
)
val cardComparator = Comparator<Hand> { c1, c2 -> compareCards(c1.value, c2.value) }
fun compareCards(c1: String, c2: String): Int {
    for (i in c1.indices) {
        if (handStrength[c1[i]]!! > handStrength[c2[i]]!!) return 1
        else if (handStrength[c1[i]]!! < handStrength[c2[i]]!!) return -1
    }
    return 0
}

