package src

import java.util.Collections.min

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day05.txt")
fun main() {
    part2()
}

private fun part2() {
    val seeds = day3Data.first().split(":").last().trim().split(" ").chunked(2)
        .map { SeedNumber(it.first().toLong(), it.last().toLong()) }
    var currentMap = ""
    val seedToSoilConstat = "seed-to-soil map:"
    val seedToSoilItemData = mutableListOf<ItemData>()
    val soilToFertilizerConstat = "soil-to-fertilizer map:"
    val soilToFertilizerItemData = mutableListOf<ItemData>()
    val fertilizerToWaterConstat = "fertilizer-to-water map:"
    val fertilizerToWaterItemData = mutableListOf<ItemData>()
    val waterToLightConstat = "water-to-light map:"
    val waterToLightItemData = mutableListOf<ItemData>()
    val lightToTemperatureConstat = "light-to-temperature map:"
    val lightToTemperatureItemData = mutableListOf<ItemData>()
    val temperatureToHumidityConstat = "temperature-to-humidity map:"
    val temperatureToHumidityItemData = mutableListOf<ItemData>()
    val humidityToLocationConstat = "humidity-to-location map:"
    val humidityToLocationItemData = mutableListOf<ItemData>()
    day3Data.forEach { line ->
        if (line.isBlank()) {
            currentMap = ""
        }
        if (line.contains(seedToSoilConstat) || currentMap == seedToSoilConstat) {
            currentMap = seedToSoilConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                seedToSoilItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(soilToFertilizerConstat) || currentMap == soilToFertilizerConstat) {
            currentMap = soilToFertilizerConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                soilToFertilizerItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(fertilizerToWaterConstat) || currentMap == fertilizerToWaterConstat) {
            currentMap = fertilizerToWaterConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                fertilizerToWaterItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(waterToLightConstat) || currentMap == waterToLightConstat) {
            currentMap = waterToLightConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                waterToLightItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(lightToTemperatureConstat) || currentMap == lightToTemperatureConstat) {
            currentMap = lightToTemperatureConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                lightToTemperatureItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(temperatureToHumidityConstat) || currentMap == temperatureToHumidityConstat) {
            currentMap = temperatureToHumidityConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                temperatureToHumidityItemData.add(
                    ItemData(
                        linesElements[1],
                        linesElements.first(),
                        linesElements.last()
                    )
                )
            }
        }
        if (line.contains(humidityToLocationConstat) || currentMap == humidityToLocationConstat) {
            currentMap = humidityToLocationConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                humidityToLocationItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }

    }

    val locations = mutableListOf<Long>()
    var minLocation = Long.MAX_VALUE
    var currentValue: Long
    seeds.forEach { seed ->
        for (it in seed.start..(seed.start + seed.length)) {
            currentValue = it.toLong()
            // This(run lit@) just allow us to break the loop when we find the value
            run lit@{
                seedToSoilItemData.forEach { itemData ->
                    if (it.toLong() in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue = it.toLong() + (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            //we have the same logic for run lit@ above
            run lit@{
                soilToFertilizerItemData.forEach { itemData ->
                    if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue += (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            run lit@{
                fertilizerToWaterItemData.forEach { itemData ->
                    if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue += (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            run lit@{
                waterToLightItemData.forEach { itemData ->
                    if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue += (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            run lit@{
                lightToTemperatureItemData.forEach { itemData ->
                    if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue += (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            run lit@{
                temperatureToHumidityItemData.forEach { itemData ->
                    if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue += (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            run lit@{
                humidityToLocationItemData.forEach { itemData ->
                    if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                        currentValue += (itemData.to - itemData.from)
                        return@lit
                    }
                }
            }
            //   locations.add(currentValue)
            if (currentValue < minLocation) {
                minLocation = currentValue
            }
        }
    }
    println(minLocation)
    //println(min(locations))
}

/*private fun part1() {
    val seeds = day3Data.first().split(":").last().trim().split(" ")
    var currentMap = ""
    val seedToSoilConstat = "seed-to-soil map:"
    val seedToSoilItemData = mutableListOf<ItemData>()
    val soilToFertilizerConstat = "soil-to-fertilizer map:"
    val soilToFertilizerItemData = mutableListOf<ItemData>()
    val fertilizerToWaterConstat = "fertilizer-to-water map:"
    val fertilizerToWaterItemData = mutableListOf<ItemData>()
    val waterToLightConstat = "water-to-light map:"
    val waterToLightItemData = mutableListOf<ItemData>()
    val lightToTemperatureConstat = "light-to-temperature map:"
    val lightToTemperatureItemData = mutableListOf<ItemData>()
    val temperatureToHumidityConstat = "temperature-to-humidity map:"
    val temperatureToHumidityItemData = mutableListOf<ItemData>()
    val humidityToLocationConstat = "humidity-to-location map:"
    val humidityToLocationItemData = mutableListOf<ItemData>()
    day3Data.forEach { line ->
        if (line.isBlank()) {
            currentMap = ""
        }
        if (line.contains(seedToSoilConstat) || currentMap == seedToSoilConstat) {
            currentMap = seedToSoilConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                seedToSoilItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(soilToFertilizerConstat) || currentMap == soilToFertilizerConstat) {
            currentMap = soilToFertilizerConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                soilToFertilizerItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(fertilizerToWaterConstat) || currentMap == fertilizerToWaterConstat) {
            currentMap = fertilizerToWaterConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                fertilizerToWaterItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(waterToLightConstat) || currentMap == waterToLightConstat) {
            currentMap = waterToLightConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                waterToLightItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(lightToTemperatureConstat) || currentMap == lightToTemperatureConstat) {
            currentMap = lightToTemperatureConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                lightToTemperatureItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }
        if (line.contains(temperatureToHumidityConstat) || currentMap == temperatureToHumidityConstat) {
            currentMap = temperatureToHumidityConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                temperatureToHumidityItemData.add(
                    ItemData(
                        linesElements[1],
                        linesElements.first(),
                        linesElements.last()
                    )
                )
            }
        }
        if (line.contains(humidityToLocationConstat) || currentMap == humidityToLocationConstat) {
            currentMap = humidityToLocationConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                humidityToLocationItemData.add(ItemData(linesElements[1], linesElements.first(), linesElements.last()))
            }
        }

    }

    val locations = mutableListOf<Long>()
    var currentValue: Long
    seeds.forEach {
        currentValue = it.toLong()
        // This(run lit@) just allow us to break the loop when we find the value
        run lit@{
            seedToSoilItemData.forEach { itemData ->
                if (it.toLong() in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue = it.toLong() + (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        //we have the same logic for run lit@ above
        run lit@{
            soilToFertilizerItemData.forEach { itemData ->
                if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue += (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        run lit@{
            fertilizerToWaterItemData.forEach { itemData ->
                if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue += (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        run lit@{
            waterToLightItemData.forEach { itemData ->
                if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue += (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        run lit@{
            lightToTemperatureItemData.forEach { itemData ->
                if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue += (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        run lit@{
            temperatureToHumidityItemData.forEach { itemData ->
                if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue += (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        run lit@{
            humidityToLocationItemData.forEach { itemData ->
                if (currentValue in (itemData.from..(itemData.from + itemData.size))) {
                    currentValue += (itemData.to - itemData.from)
                    return@lit
                }
            }
        }
        locations.add(currentValue)
    }
    println(locations)
    println(min(locations))
}*/

data class ItemData(val from: Long = -1, val to: Long = -1, val size: Long = -1)
data class SeedNumber(val start: Long = -1, val length: Long = -1)