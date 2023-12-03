package src

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day03.txt")
fun main() {
    partOneAndTwo()
}

val symboles = mutableSetOf<Symbole>()
val partNumbers = mutableListOf<PartNumber>()
private fun partOneAndTwo() {
    val rows = day3Data.first().length
    val cols = day3Data.size
    var partOneResult: Int = 0
    var secondPartResult: Int = 0
    for (row in 0..<rows) {
        var currentNumber = PartNumber(row = row)
        for (col in 0..<cols) {
            if (day3Data[row][col].isDigit()) {
                currentNumber = checkIfNeighborSymbol(row, col, currentNumber)
                if (currentNumber.startIndex == -1) {
                    currentNumber = currentNumber.copy(startIndex = col, endIndex = col)
                } else {
                    currentNumber = currentNumber.copy(endIndex = col)
                }
                if (col == cols - 1) {
                    val pair = addNumberToTotal(currentNumber, row, partOneResult)
                    currentNumber = pair.first
                    partOneResult = pair.second
                }
            } else {
                if (currentNumber.startIndex != -1) {
                    val pair = addNumberToTotal(currentNumber, row, partOneResult)
                    currentNumber = pair.first
                    partOneResult = pair.second
                }
            }
        }
    }
    symboles.forEach { symbole ->
        val pair = partNumbers.filter { it.symbole != null && it.symbole == symbole }
        if (pair.size == 2) {
            secondPartResult += (pair.first().value.toInt() * pair.last().value.toInt())
        }
    }
    println(partOneResult)
    println(secondPartResult)
}

private fun addNumberToTotal(currentNumber: PartNumber, row: Int, total: Int): Pair<PartNumber, Int> {
    var currentNumber1 = currentNumber
    var total1 = total
    if (currentNumber1.startIndex != -1) {
        currentNumber1 = currentNumber1.copy(value = day3Data[row].substring(currentNumber1.startIndex, currentNumber1.endIndex + 1))
        if (currentNumber1.isAdjacent) {
            total1 += currentNumber1.value.toInt()
            if (currentNumber1.symbole != null) {
                partNumbers.add(currentNumber1)
            }
        }
        currentNumber1 = PartNumber()
    }
    return Pair(currentNumber1, total1)
}

private fun checkIfNeighborSymbol(row: Int, col: Int, currentNumber: PartNumber): PartNumber {
    var currentNumber1 = currentNumber
    for (i in row - 1..row + 1) {
        for (j in col - 1..col + 1) {
            if (checkIfIsSymboleAndGetIt(i, j).first) {
                currentNumber1 = currentNumber.copy(isAdjacent = true, symbole = checkIfIsSymboleAndGetIt(i, j).second)
                if (checkIfIsSymboleAndGetIt(i, j).second != null) {
                    symboles.add(checkIfIsSymboleAndGetIt(i, j).second!!)
                }
                break
            }
        }
    }
    return currentNumber1
}

private fun checkIfIsSymboleAndGetIt(row: Int, col: Int): Pair<Boolean, Symbole?> {
    val isSymbole = day3Data.getOrNull(row)?.getOrNull(col) != null &&
            !day3Data.getOrNull(row)!!.getOrNull(col)!!.isDigit() &&
            day3Data.getOrNull(row)!!.getOrNull(col)!!.toString() != "."
    if (isSymbole) {
        if (day3Data[row][col].toString() == "*")
            return Pair(true, Symbole(row = row, col = col))
        else
            return Pair(true, null)
    }
    return Pair(false, null)
}

data class PartNumber(val value: String = "", val row: Int = -1, val startIndex: Int = -1, val endIndex: Int = -1,
                      val isAdjacent: Boolean = false,
                      val symbole: Symbole? = null
)

data class Symbole(val value: String = "*", val col: Int, val row: Int)