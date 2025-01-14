plugins {
    id("java-library")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
dependencies {
    implementation(libs.media3.common)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.afeiship.jsw_nx"
            artifactId = "jsw_nx"
            version = "0.0.2"
        }
    }
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}