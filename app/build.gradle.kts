import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    application
    id("org.openjfx.javafxplugin") version ("0.0.13")
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "media",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win")

dependencies {
    val junitJupiterVersion = "5.9.1" // Usa l'ultima versione di JUnit 5
    val javaFxVersion = "17"
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("org.json:json:20230227")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //decodificare stringhe
    implementation ("org.apache.commons:commons-text:1.11.0")

}

tasks.test {
    useJUnitPlatform() // Usa JUnit 5
    testLogging {
		events(*(TestLogEvent.values()))
	}
    testLogging.showStandardStreams = true
}

application {
    // Define the main class for the application.
    mainClass.set("it.unibo.io.Main")
}


