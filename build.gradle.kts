plugins {
    id("java")
}

group = "com.minegun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.minestom:minestom:2026.03.03-1.21.11")
}

tasks.test {
    useJUnitPlatform()
}