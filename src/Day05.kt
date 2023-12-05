package src

import java.util.Collections.max
import java.util.Collections.min

private val day3Data = readFileAsLinesUsingBufferedReader("src/Day05.txt")
fun main() {
    val seeds = day3Data.first().split(":").last().trim().split(" ")
    var currentMap = ""
    val seedToSoilMap = mutableMapOf<Long, Long>()
    val seedToSoilConstat = "seed-to-soil map:"
    val soilToFertilizerMap = mutableMapOf<Long, Long>()
    val soilToFertilizerConstat = "soil-to-fertilizer map:"
    val fertilizerToWaterMap = mutableMapOf<Long, Long>()
    val fertilizerToWaterConstat = "fertilizer-to-water map:"
    val waterToLightMap = mutableMapOf<Long, Long>()
    val waterToLightConstat = "water-to-light map:"
    val lightToTemperatureMap = mutableMapOf<Long, Long>()
    val lightToTemperatureConstat = "light-to-temperature map:"
    val temperatureToHumidityMap = mutableMapOf<Long, Long>()
    val temperatureToHumidityConstat = "temperature-to-humidity map:"
    val humidityToLocationMap = mutableMapOf<Long, Long>()
    val humidityToLocationConstat = "humidity-to-location map:"
    day3Data.forEach { line ->
        if (line.isBlank()) {
            currentMap = ""
        }
        if (line.contains(seedToSoilConstat) || currentMap == seedToSoilConstat) {
            currentMap = seedToSoilConstat
            if (line != currentMap) {
                val linesElements = line.split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    seedToSoilMap[linesElements[1] + i] = linesElements.first() + i

            }
        }
        if (line.contains(soilToFertilizerConstat) || currentMap == soilToFertilizerConstat) {
            currentMap = soilToFertilizerConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    soilToFertilizerMap[linesElements[1] + i] = linesElements.first() + i
            }
        }
        if (line.contains(fertilizerToWaterConstat) || currentMap == fertilizerToWaterConstat) {
            currentMap = fertilizerToWaterConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    fertilizerToWaterMap[linesElements[1] + i] = linesElements.first() + i
            }
        }
        if (line.contains(waterToLightConstat) || currentMap == waterToLightConstat) {
            currentMap = waterToLightConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    waterToLightMap[linesElements[1] + i] = linesElements.first() + i
            }
        }
        if (line.contains(lightToTemperatureConstat) || currentMap == lightToTemperatureConstat) {
            currentMap = lightToTemperatureConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    lightToTemperatureMap[linesElements[1] + i] = linesElements.first() + i
            }
        }
        if (line.contains(temperatureToHumidityConstat) || currentMap == temperatureToHumidityConstat) {
            currentMap = temperatureToHumidityConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    temperatureToHumidityMap[linesElements[1] + i] = linesElements.first() + i
            }
        }
        if (line.contains(humidityToLocationConstat) || currentMap == humidityToLocationConstat) {
            currentMap = humidityToLocationConstat
            if (line != currentMap) {
                val linesElements = line.trim().split(" ").map { it.toLong() }
                for (i in 0..<linesElements.last())
                    humidityToLocationMap[linesElements[1] + i] = linesElements.first() + i
            }
        }

        println(line)
    }

    val maxValuesOfSeedToSoil = max(seedToSoilMap.keys + seedToSoilMap.values)
    for (i in 0..maxValuesOfSeedToSoil) {
        if (seedToSoilMap.getOrDefault(i, -1L) == -1L) {
            seedToSoilMap[i] = i
        }
    }
    val maxValuesOfSoilToFertilizer = max(soilToFertilizerMap.keys + soilToFertilizerMap.values)
    for (i in 0..maxValuesOfSoilToFertilizer) {
        if (soilToFertilizerMap.getOrDefault(i, -1L) == -1L) {
            soilToFertilizerMap[i] = i
        }
    }
    val maxValuesOfFertilizerToWater = max(fertilizerToWaterMap.keys + fertilizerToWaterMap.values)
    for (i in 0..maxValuesOfFertilizerToWater) {
        if (fertilizerToWaterMap.getOrDefault(i, -1L) == -1L) {
            fertilizerToWaterMap[i] = i
        }
    }
    val maxValuesOfWaterToLight = max(waterToLightMap.keys + waterToLightMap.values)
    for (i in 0..maxValuesOfWaterToLight) {
        if (waterToLightMap.getOrDefault(i, -1L) == -1L) {
            waterToLightMap[i] = i
        }
    }
    val maxValuesOfLightToTemperature = max(lightToTemperatureMap.keys + lightToTemperatureMap.values)
    for (i in 0..maxValuesOfLightToTemperature) {
        if (lightToTemperatureMap.getOrDefault(i, -1L) == -1L) {
            lightToTemperatureMap[i] = i
        }
    }
    val maxValuesOfTemperatureToHumidity = max(temperatureToHumidityMap.keys + temperatureToHumidityMap.values)
    for (i in 0..maxValuesOfTemperatureToHumidity) {
        if (temperatureToHumidityMap.getOrDefault(i, -1L) == -1L) {
            temperatureToHumidityMap[i] = i
        }
    }
    val maxValuesOfHumidityToLocation = max(humidityToLocationMap.keys + humidityToLocationMap.values)
    for (i in 0..maxValuesOfHumidityToLocation) {
        if (humidityToLocationMap.getOrDefault(i, -1L) == -1L) {
            humidityToLocationMap[i] = i
        }
    }
    val locations = mutableListOf<Long>()
    seeds.forEach {
        val soil = seedToSoilMap.getOrDefault(it.toLong(), it.toLong())
        val fertilizer = soilToFertilizerMap.getOrDefault(soil, soil)
        val water = fertilizerToWaterMap.getOrDefault(fertilizer, fertilizer)
        val light = waterToLightMap.getOrDefault(water, water)
        val temperature = lightToTemperatureMap.getOrDefault(light, light)
        val humidity = temperatureToHumidityMap.getOrDefault(temperature, temperature)
        val location = humidityToLocationMap.getOrDefault(humidity, humidity)
        locations.add(location)
    }
    println("result")
    println(locations)
    println(min(locations))

}