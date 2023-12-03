package src


private val day3Data = readFileAsLinesUsingBufferedReader("src/Day03.txt")
fun main() {
    part1()
}

private fun part1() {
    val rows = day3Data.first().length
    val cols = day3Data.size
    var total: Int = 0
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
                    val pair = addNumberToTotal(currentNumber, row, total)
                    currentNumber = pair.first
                    total = pair.second
                }
            } else {
                if (currentNumber.startIndex != -1) {
                    val pair = addNumberToTotal(currentNumber, row, total)
                    currentNumber = pair.first
                    total = pair.second
                }
            }
        }

    }
    print(total)
}

private fun addNumberToTotal(currentNumber: PartNumber, row: Int, total: Int): Pair<PartNumber, Int> {
    var currentNumber1 = currentNumber
    var total1 = total
    if (currentNumber1.startIndex != -1) {
        currentNumber1 = currentNumber1.copy(value = day3Data[row].substring(currentNumber1.startIndex, currentNumber1.endIndex + 1))
        if (currentNumber1.isAdjacent) {
            total1 += currentNumber1.value.toInt()
        }
        currentNumber1 = PartNumber()
    }
    return Pair(currentNumber1, total1)
}

private fun checkIfNeighborSymbol(row: Int, col: Int, currentNumber: PartNumber): PartNumber {
    var currentNumber1 = currentNumber
    if (
            checkIfIsSymbole(row - 1, col - 1) ||
            checkIfIsSymbole(row - 1, col) ||
            checkIfIsSymbole(row - 1, col + 1) ||
            checkIfIsSymbole(row, col - 1) ||
            checkIfIsSymbole(row, col + 1) ||
            checkIfIsSymbole(row + 1, col - 1) ||
            checkIfIsSymbole(row + 1, col) ||
            checkIfIsSymbole(row + 1, col + 1)


    ) {
        currentNumber1 = currentNumber1.copy(isAdjacent = true)
    }
    return currentNumber1
}

private fun checkIfIsSymbole(row: Int, col: Int): Boolean {
    return day3Data.getOrNull(row)?.getOrNull(col) != null &&
            !day3Data.getOrNull(row)!!.getOrNull(col)!!.isDigit() &&
            day3Data.getOrNull(row)!!.getOrNull(col)!!.toString() != "."
}

data class PartNumber(
        val value: String = "",
        val row: Int = -1,
        val startIndex: Int = -1,
        val endIndex: Int = -1,
        val isAdjacent: Boolean = false,
)