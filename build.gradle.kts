plugins {
    kotlin("jvm") version "1.9.21"
}

sourceSets {
    src.main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
