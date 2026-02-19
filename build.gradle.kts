plugins {
    id("java")
}

group = "br.com.softhouse.dende"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.github.lasilva:dendeframework:1.0.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("run") {
    group = "application"
    mainClass.set("br.com.softhouse.dende.DendeEventosApplication")
    classpath = sourceSets["main"].runtimeClasspath
}