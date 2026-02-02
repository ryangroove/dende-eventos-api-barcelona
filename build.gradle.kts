plugins {
    id("java")
}

group = "br.com.softhouse.dende"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.github.lasilva:dendeframework:1.0-SNAPSHOT")

}

tasks.test {
    useJUnitPlatform()
}