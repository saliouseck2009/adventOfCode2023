
var lines = readFileAsLinesUsingBufferedReader("Day02.txt")
val green = "green" ;val red = "red" ;val blue = "blue"
val cubsOnBag = mapOf(green to 13, red to 12,blue to 14)
fun main(){
    println(firstChallenge())
    println(secondChallenge())
}
private fun firstChallenge(): Int{
    var total = 0
    lines.forEachIndexed { position, line ->
        val games = line.split(":", ";")
        var isPossible = true
        games.drop(1).forEachIndexed { index, game ->
            game.trim().split(",").forEach { item ->
                val values = item.trim().split(" ")
                val currentValue= values.first().toInt()
                if (values.last() == green && currentValue  > cubsOnBag[green]!!) {
                    isPossible = false
                } else if (values.last() == red && currentValue  > cubsOnBag[red]!!) {
                    isPossible = false
                } else if (values.last() == blue && currentValue  > cubsOnBag[blue]!!) {
                    isPossible = false
                }
            }
        }
        if(isPossible){ total += position+1 }
    }
    return total
}
private fun secondChallenge() : Int{
    var total = 0
    lines.forEach {  line ->
        val games = line.split(":", ";")
        val fewestNumberOfCube=  mutableMapOf(green to 0, red to 0,blue to 0)
        games.drop(1).forEach { game ->
            game.trim().split(",").forEach { item ->
                val values = item.trim().split(" ")
                val currentValues =  values.first().toInt()
                if (values.last() == green && currentValues>fewestNumberOfCube[green]!!) {
                    fewestNumberOfCube[green]= currentValues
                } else if (values.last() == red && currentValues> fewestNumberOfCube[red]!!) {
                   fewestNumberOfCube[red]=currentValues
                } else if (values.last() == blue && currentValues> fewestNumberOfCube[blue]!!) {
                    fewestNumberOfCube[blue]=currentValues
                } }
        }
        val produit = fewestNumberOfCube[green]!!*fewestNumberOfCube[red]!!*fewestNumberOfCube[blue]!!
        total +=produit
    }
    return total
}
