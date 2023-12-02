val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val numbersMap = mapOf("one" to '1', "two" to '2', "three" to '3', "four" to '4', "five" to '5', "six" to '6', "seven" to '7', "eight" to '8', "nine" to '9')

fun main(){
   print (secondChallenge())
}
private fun firstChallenge() : Int{
    val lines = readFileAsLinesUsingBufferedReader("Day01.txt")
    var sum = 0
    lines.forEach {
        val numberInLine = it.filter {
            it.isDigit()
        }
        sum += "${numberInLine.first()}${numberInLine.last()}".toInt()
    }
    return sum
}
private fun secondChallenge() : Int{
    val lines = readFileAsLinesUsingBufferedReader("data2.txt")
    var sum = 0
    lines.forEach {word->
        var digits = mutableListOf<Char>()
        word.forEachIndexed { index, c ->
            if(c.isDigit()){
                digits.add(c)
            }else{
                for (i in index+2..word.length){
                    if(numbers.contains(word.substring(index,i))){
                        numbersMap.get(word.substring(index,i))?.let { digits.add(it) }
                    }
                }
            }
        }
        sum += "${digits.first()}${digits.last()}".toInt()
    }
    return sum
}

